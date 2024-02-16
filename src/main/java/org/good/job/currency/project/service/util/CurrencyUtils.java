package org.good.job.currency.project.service.util;

import java.util.Currency;
import java.util.function.Predicate;


public class CurrencyUtils {

    public static boolean isSupportedCurrency(String currencyCode) {
        return Currency.getAvailableCurrencies()
                .stream()
                .map(Currency::getCurrencyCode)
                .anyMatch(Predicate.isEqual(currencyCode));
    }

}
