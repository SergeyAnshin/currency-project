package org.good.job.currency.project.bot.service.action;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.bot.config.CurrencyBotMessages;
import org.good.job.currency.project.bot.dao.UserDao;
import org.good.job.currency.project.bot.model.UserState;
import org.good.job.currency.project.controller.RateController;
import org.good.job.currency.project.entity.RateStatisticData;
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
public class GetStatisticAction implements Action {

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
        String receivedDateRange = message.getText();
        LocalDate startDate;
        LocalDate endDate;
        ResponseEntity<RateStatisticData> externalApiCurrencyRateStatistics;
        try {
            String[] receivedDates = receivedDateRange.trim().split(" ");
            if (receivedDates.length != 2) throw new Exception();
            startDate = LocalDate.parse(receivedDates[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            endDate = LocalDate.parse(receivedDates[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            externalApiCurrencyRateStatistics = rateController.getCurrencyRateStatistics(
                    currentUserState.getExternalApiName(), currentUserState.getCurrency(), startDate, endDate);
        } catch (RateNotFoundException e) {
            sendMessage.setText(String.format(currencyBotMessages.rateDoesNotSupportDate,
                                              currentUserState.getExternalApiName()));
            return sendMessage;
        } catch (Exception e) {
            sendMessage.setText(currencyBotMessages.incorrectDateInput);
            return sendMessage;
        }

        if (!externalApiCurrencyRateStatistics.getStatusCode().is2xxSuccessful()) {
            sendMessage.setText(currencyBotMessages.bankStatisticsError);
            return sendMessage;
        }
        RateStatisticData rateStatisticData = externalApiCurrencyRateStatistics.getBody();
        sendMessage.setText(String.format(currencyBotMessages.statisticsMessage,
                                          rateStatisticData.avgBuyRate,
                                          rateStatisticData.avgSellRate,
                                          rateStatisticData.minBuyRate,
                                          rateStatisticData.minSellRate,
                                          rateStatisticData.maxBuyRate,
                                          rateStatisticData.maxSellRate,
                                          "rateStatisticData.buyRateByDate",
                                          "rateStatisticData.sellRateByDate"
        ));
        return sendMessage;
    }

}
