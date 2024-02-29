package org.good.job.currency.project.bot.service;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.bot.config.BotMessageProvider;
import org.good.job.currency.project.bot.config.CurrencyBotConfig;
import org.good.job.currency.project.bot.dao.UserDao;
import org.good.job.currency.project.bot.model.UserState;
import org.good.job.currency.project.bot.model.enums.UserStep;
import org.good.job.currency.project.bot.service.action.ActionFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@RequiredArgsConstructor

@Component
public class CurrencyBot extends TelegramLongPollingBot {


    private final CurrencyBotConfig currencyBotConfig;
    private final ActionFactory actionFactory;
    private final UserDao userDao;
    private final BotMessageProvider botMessageProvider;

    @Override
    public void onUpdateReceived(Update update) {
        String startCommand = botMessageProvider.getMessage("bot.commands.StartCommand");
        if (update.hasMessage() && update.getMessage().hasText()) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            String chatId = update.getMessage().getChatId().toString();
            if (update.getMessage().getText().equals(startCommand)) {
                userDao.getUserStateHashMap().put(chatId, new UserState(UserStep.START));
            }
            UserState currentUserState = userDao.getUserStateHashMap().get(chatId);
            SendMessage messageToHandle =
                    actionFactory.getAction(currentUserState.getCurrentStep()).handle(update);

            executeMethod(messageToHandle);

        }
    }

    @Override
    public String getBotUsername() {
        return currencyBotConfig.getBotName();
    }


    @Override
    public String getBotToken() {
        return currencyBotConfig.getBotToken();
    }

    public void executeMethod(SendMessage toHandle) {
        try {
            execute(toHandle);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
