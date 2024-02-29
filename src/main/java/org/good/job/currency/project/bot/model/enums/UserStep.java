package org.good.job.currency.project.bot.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.good.job.currency.project.bot.service.action.*;


@AllArgsConstructor
@Getter
public enum UserStep {
    START(toCamelCase(StartAction.class.getSimpleName())),
    SELECT_BANK(toCamelCase(SelectBankAction.class.getSimpleName())),
    SELECT_CURRENCY(toCamelCase(SelectCurrencyAction.class.getSimpleName())),
    SELECT_FEATURE(toCamelCase(SelectFeatureAction.class.getSimpleName())),
    GET_CURRENCY_RATE_TODAY(toCamelCase(GetCurrencyRateTodayAction.class.getSimpleName())),
    GET_CURRENCY_RATE_BY_DATE(toCamelCase(GetCurrencyRateByDateAction.class.getSimpleName())),
    GET_STATISTIC(toCamelCase(GetStatisticAction.class.getSimpleName()));

    private final String actionBeanName;

    private static String toCamelCase(String actionBeanName) {
        return Character.toLowerCase(actionBeanName.charAt(0)) + actionBeanName.substring(1);
    }
}
