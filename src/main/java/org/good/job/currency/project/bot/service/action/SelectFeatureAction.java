package org.good.job.currency.project.bot.service.action;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.bot.config.CurrencyBotMessages;
import org.good.job.currency.project.bot.dao.UserDao;
import org.good.job.currency.project.bot.model.UserState;
import org.good.job.currency.project.bot.model.enums.UserStep;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class SelectFeatureAction implements Action {

    private final CurrencyBotMessages currencyBotMessages;
    private final UserDao userDao;
    private final StartAction startAction;
    private final SelectBankAction selectBankAction;
    private final SelectCurrencyAction selectCurrencyAction;
    private final GetCurrencyRateTodayAction getCurrencyRateTodayAction;

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        UserState currentUserState = userDao.getUserStateHashMap().get(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (message.getText().equals(currencyBotMessages.reselectBankMessage)) {
            currentUserState.setCurrentStep(UserStep.SELECT_BANK);
            currentUserState.setExternalApiName(null);
            currentUserState.setCurrency("");
            sendMessage.setText(currencyBotMessages.reselectBankMessage + ":");
            sendMessage.setReplyMarkup(startAction.makeKeyboard());
        } else if (message.getText().equals(currencyBotMessages.reselectCurrencyMessage)) {
            currentUserState.setCurrentStep(UserStep.SELECT_CURRENCY);
            currentUserState.setCurrency("");
            sendMessage.setText(currencyBotMessages.reselectCurrencyMessage + ":");
            sendMessage.setReplyMarkup(selectBankAction.makeKeyboard(currentUserState));
        } else {
            UserStep nextUserStep = currencyBotMessages.featureSelectStringsMap.get(message.getText());
            if (isNull(nextUserStep)) {
                sendMessage.setText(currencyBotMessages.incorrectFeatureInput);
                sendMessage.setReplyMarkup(selectCurrencyAction.makeKeyboard());
            } else {
                currentUserState.setCurrentStep(nextUserStep);
                switch (nextUserStep) {
                    case GET_CURRENCY_RATE_TODAY -> sendMessage = getCurrencyRateTodayAction.handle(update);
                    case GET_CURRENCY_RATE_BY_DATE -> sendMessage.setText(currencyBotMessages.inputDateMessage);
                    case GET_STATISTIC -> sendMessage.setText(currencyBotMessages.inputRangeOfDatesMessage);
                }
            }
        }
        return sendMessage;
    }

}
