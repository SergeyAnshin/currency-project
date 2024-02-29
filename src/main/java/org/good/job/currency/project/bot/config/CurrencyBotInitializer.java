package org.good.job.currency.project.bot.config;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.bot.service.CurrencyBot;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@RequiredArgsConstructor

@Component
public class CurrencyBotInitializer {

    private final CurrencyBot currencyBot;

    @EventListener({ ContextRefreshedEvent.class })
    public void init() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(currencyBot);
        } catch (TelegramApiException e) {
            e.fillInStackTrace();
        }
    }

}
