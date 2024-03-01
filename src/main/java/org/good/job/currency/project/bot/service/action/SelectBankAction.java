package org.good.job.currency.project.bot.service.action;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.bot.config.CurrencyBotMessages;
import org.good.job.currency.project.bot.dao.UserDao;
import org.good.job.currency.project.bot.model.UserState;
import org.good.job.currency.project.bot.model.enums.UserStep;
import org.good.job.currency.project.controller.CurrencyController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.isNull;


@RequiredArgsConstructor
@Service
public class SelectBankAction implements Action {

    private final Integer buttonsCountInRow = 5;

    private final CurrencyBotMessages currencyBotMessages;
    private final UserDao userDao;
    private final CurrencyController currencyController;
    private final StartAction startAction;


    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        UserState currentUserState = userDao.getUserStateHashMap().get(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        currentUserState.setExternalApiName(message.getText());
        ReplyKeyboardMarkup replyKeyboardMarkup = makeKeyboard(currentUserState);
        if (isNull(replyKeyboardMarkup)) {
            sendMessage.setText(String.format(currencyBotMessages.bankIsNotSupportedMessage,
                                              currentUserState.getExternalApiName()));
            sendMessage.setReplyMarkup(startAction.makeKeyboard());
            currentUserState.setExternalApiName("");
            return sendMessage;
        }
        sendMessage.setText(String.format(currencyBotMessages.selectedBankMessage, message.getText()));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        currentUserState.setCurrentStep(UserStep.SELECT_CURRENCY);
        return sendMessage;
    }

    public ReplyKeyboardMarkup makeKeyboard(UserState currentUserState) {
        List<KeyboardRow> rows = new ArrayList<>();
        ResponseEntity<List<String>> availableCurrenciesByBankName =
                currencyController.getAvailableCurrenciesByBankName(currentUserState.getExternalApiName());
        if (!availableCurrenciesByBankName.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        Iterator<String> currencyIterator = availableCurrenciesByBankName.getBody().iterator();
        while (currencyIterator.hasNext()) {
            KeyboardRow row = new KeyboardRow();
            for (int i = 0; i < buttonsCountInRow; i++) {
                if (!currencyIterator.hasNext()) break;
                row.add(currencyIterator.next());
            }
            rows.add(row);
        }

        rows.add(new KeyboardRow(List.of(new KeyboardButton(currencyBotMessages.reselectBankMessage))));

        return ReplyKeyboardMarkup.builder()
                .keyboard(rows)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();
    }

}
