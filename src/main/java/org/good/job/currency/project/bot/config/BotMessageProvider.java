package org.good.job.currency.project.bot.config;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.bot.model.enums.UserStep;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;


@RequiredArgsConstructor

@Component
public class BotMessageProvider {

    private final MessageSource messageSource;

    public String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public String getMessage(UserStep userStep) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("bot.action.button-text.by-user-step." + userStep.name(),
                                        null,
                                        locale);
    }

}
