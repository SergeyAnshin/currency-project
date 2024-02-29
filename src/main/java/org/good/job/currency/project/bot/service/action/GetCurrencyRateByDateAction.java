package org.good.job.currency.project.bot.service.action;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.bot.config.CurrencyBotMessages;
import org.good.job.currency.project.bot.dao.UserDao;
import org.good.job.currency.project.bot.model.UserState;
import org.good.job.currency.project.controller.RateController;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.service.exception.RateNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RequiredArgsConstructor
@Service
public class GetCurrencyRateByDateAction implements Action {

    private final CurrencyBotMessages currencyBotMessages;
    private final UserDao userDao;
    private final RateController rateController;

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        UserState currentUserState = userDao.getUserStateHashMap().get(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        String receivedDate = message.getText();
        LocalDate date;
        ResponseEntity<GeneralRate> externalApiCurrencyRateByDate;
        try {
            date = LocalDate.parse(receivedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            externalApiCurrencyRateByDate = rateController.getExternalApiCurrencyRateByDate(
                    currentUserState.getExternalApiName(), currentUserState.getCurrency(), date);
        } catch (RateNotFoundException e) {
            sendMessage.setText(String.format(currencyBotMessages.rateDoesNotSupportDate,
                                              currentUserState.getExternalApiName()));
            return sendMessage;
        } catch (Exception e) {
            sendMessage.setText(currencyBotMessages.incorrectDateInput);
            return sendMessage;
        }

        if (!externalApiCurrencyRateByDate.getStatusCode().is2xxSuccessful()) {
            sendMessage.setText(currencyBotMessages.bankCurrencyByDateError);
            return sendMessage;
        }
        GeneralRate generalRate = externalApiCurrencyRateByDate.getBody();
        sendMessage.setText(String.format(currencyBotMessages.currencyByDateMessage,
                                          currentUserState.getExternalApiName(),
                                          currentUserState.getCurrency(),
                                          generalRate.getDate().toString(),
                                          generalRate.getSellRate(),
                                          generalRate.getBuyRate()
        ));
        return sendMessage;
    }

}
