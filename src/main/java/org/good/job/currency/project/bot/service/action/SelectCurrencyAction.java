package org.good.job.currency.project.bot.service.action;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.bot.config.BotMessageProvider;
import org.good.job.currency.project.bot.config.CurrencyBotMessages;
import org.good.job.currency.project.bot.dao.UserDao;
import org.good.job.currency.project.bot.model.UserState;
import org.good.job.currency.project.bot.model.enums.UserStep;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class SelectCurrencyAction implements Action {

    private final CurrencyBotMessages currencyBotMessages;
    private final BotMessageProvider botMessageProvider;
    private final UserDao userDao;
    private final StartAction startAction;


    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        UserState currentUserState = userDao.getUserStateHashMap().get(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (message.getText().equals(currencyBotMessages.reselectBankMessage)) {
            currentUserState.setCurrentStep(UserStep.SELECT_BANK);
            currentUserState.setExternalApiName("");
            sendMessage.setText(currencyBotMessages.reselectBankMessage + ":");
            sendMessage.setReplyMarkup(startAction.makeKeyboard());
        } else {
            // TODO make currency validation by service without Java.Currency class and use it there
            currentUserState.setCurrency(message.getText());
            currentUserState.setCurrentStep(UserStep.SELECT_FEATURE);
            sendMessage.setText(String.format(currencyBotMessages.selectedCurrencyMessage, message.getText()));
            sendMessage.setReplyMarkup(makeKeyboard());
        }
        return sendMessage;
    }

    public ReplyKeyboardMarkup makeKeyboard() {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow featuresRow = new KeyboardRow();
        featuresRow.add(botMessageProvider.getMessage(UserStep.GET_CURRENCY_RATE_TODAY));
        featuresRow.add(botMessageProvider.getMessage(UserStep.GET_CURRENCY_RATE_BY_DATE));
        featuresRow.add(botMessageProvider.getMessage(UserStep.GET_STATISTIC));
        rows.add(featuresRow);

        KeyboardRow reselectRow = new KeyboardRow();
        reselectRow.add(currencyBotMessages.reselectBankMessage);
        reselectRow.add(currencyBotMessages.reselectCurrencyMessage);
        rows.add(reselectRow);

        return ReplyKeyboardMarkup.builder()
                .keyboard(rows)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();
    }

}
