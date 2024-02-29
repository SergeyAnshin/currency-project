package org.good.job.currency.project.bot.service.action;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.bot.config.CurrencyBotMessages;
import org.good.job.currency.project.bot.dao.UserDao;
import org.good.job.currency.project.bot.model.UserState;
import org.good.job.currency.project.bot.model.enums.UserStep;
import org.good.job.currency.project.controller.ExternalApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.isNull;


@RequiredArgsConstructor
@Service
public class StartAction implements Action {

    private final Integer buttonsCountInRow = 5;

    private final CurrencyBotMessages currencyBotMessages;
    private final UserDao userDao;
    private final ExternalApiController externalApiController;


    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        UserState currentUserState = userDao.getUserStateHashMap().get(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        ReplyKeyboardMarkup replyKeyboardMarkup = makeKeyboard();
        if (isNull(replyKeyboardMarkup)) {
            sendMessage.setText(currencyBotMessages.noBanksAvailableMessage);
            return sendMessage;
        }
        currentUserState.setCurrentStep(UserStep.SELECT_BANK);
        sendMessage.setText(currencyBotMessages.selectBankMessage);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public ReplyKeyboardMarkup makeKeyboard() {
        ResponseEntity<List<String>> availableExternalApis = externalApiController.getAvailableExternalApis();
        if (!availableExternalApis.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        List<KeyboardRow> rows = new ArrayList<>();
        Iterator<String> externalApiNameIterator = availableExternalApis.getBody().iterator();
        while (externalApiNameIterator.hasNext()) {
            KeyboardRow row = new KeyboardRow();
            for (int i = 0; i < buttonsCountInRow; i++) {
                if (!externalApiNameIterator.hasNext()) break;
                row.add(externalApiNameIterator.next());
            }
            rows.add(row);
        }
        return ReplyKeyboardMarkup.builder()
                .keyboard(rows)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();
    }

}
