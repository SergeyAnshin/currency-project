package org.good.job.currency.project.service.util;

import org.good.job.currency.project.dto.GeneralExternalApiRate;

import java.util.Arrays;


public class ClassFieldRateExtractor {

    public static final String BUY_RATE_PREFIX = "BUY";
    public static final String SELL_RATE_PREFIX = "SELL";

    public static double getBuyRateByCurrencyCode(GeneralExternalApiRate externalApiRate, String currencyCode) {
        return getaDouble(externalApiRate, currencyCode, BUY_RATE_PREFIX);
    }


    public static double getSellRateByCurrencyCode(GeneralExternalApiRate externalApiRate, String currencyCode) {
        return getaDouble(externalApiRate, currencyCode, SELL_RATE_PREFIX);
    }

    private static Double getaDouble(GeneralExternalApiRate externalApiRate, String currencyCode, String prefix) {
        return Arrays.stream(externalApiRate.getClass().getDeclaredFields())
                .filter(field -> field.getName().toUpperCase().contains(prefix + currencyCode)).map(field -> {
                    try {
                        field.setAccessible(true);
                        return field.getDouble(externalApiRate);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }).findFirst().orElseThrow();
    }


}
