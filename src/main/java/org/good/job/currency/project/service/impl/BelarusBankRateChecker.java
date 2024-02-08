package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.dto.BelarusBankRate;
import org.good.job.currency.project.dto.GeneralExternalApiRate;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.service.RateChecker;
import org.good.job.currency.project.service.annotations.AssignedClass;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;


@AssignedClass(BelarusBankRate.class)

@Service
public class BelarusBankRateChecker implements RateChecker {

    public static final String BUY_RATE_PREFIX = "BUY";
    public static final String SELL_RATE_PREFIX = "SELL";

    @Override
    public boolean isRateMatchParameters(GeneralExternalApiRate externalApiRate, ExternalApiUrl externalApiUrl) {
        if (externalApiRate instanceof BelarusBankRate rate) {
            return isMatchingByCurrencyAndSpecifiedDate(rate, externalApiUrl);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isMatchingByCurrencyAndSpecifiedDate(BelarusBankRate rate, ExternalApiUrl externalApiUrl) {
        return containCurrencyInFields(rate, externalApiUrl) && LocalDate.from(rate.getDate()).equals(externalApiUrl.getDate());
    }

    private boolean containCurrencyInFields(BelarusBankRate rate, ExternalApiUrl externalApiUrl) {
        Field[] declaredFields = rate.getClass().getDeclaredFields();
        String currencyCode = externalApiUrl.getCurrency().getCurrencyCode();
        return Arrays.stream(declaredFields)
                .anyMatch(field -> field.getName().toUpperCase().contains(BUY_RATE_PREFIX + currencyCode)
                        || field.getName().toUpperCase().contains(SELL_RATE_PREFIX + currencyCode));
    }

}
