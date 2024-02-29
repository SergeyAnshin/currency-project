package org.good.job.currency.project.bot.service.action;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.bot.config.CurrencyBotMessages;
import org.good.job.currency.project.bot.dao.UserDao;
import org.good.job.currency.project.bot.model.UserState;
import org.good.job.currency.project.controller.RateController;
import org.good.job.currency.project.entity.GeneralRate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;


@RequiredArgsConstructor
@Service
public class GetCurrencyRateTodayAction implements Action {

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
        ResponseEntity<GeneralRate> externalApiCurrencyRateToday = rateController.getExternalApiCurrencyRateByDate(
                currentUserState.getExternalApiName(), currentUserState.getCurrency(), LocalDate.now());
        if (!externalApiCurrencyRateToday.getStatusCode().is2xxSuccessful()) {
            sendMessage.setText(currencyBotMessages.bankCurrencyTodayError);
            return sendMessage;
        }
        GeneralRate generalRate = externalApiCurrencyRateToday.getBody();
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
