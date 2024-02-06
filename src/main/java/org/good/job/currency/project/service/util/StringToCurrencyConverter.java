package org.good.job.currency.project.service.util;


import org.good.job.currency.project.service.exception.CurrencyNotExistsException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.Objects;


@Component
public class StringToCurrencyConverter implements Converter<String, Currency> {

    @Override
    public Currency convert(String currencyCode) {
        if (currencyCode.isBlank()) {
            throw new CurrencyNotExistsException();
        }
        return Currency.getAvailableCurrencies()
                .stream()
                .filter(currency -> Objects.equals(currency.getCurrencyCode(), currencyCode))
                .findFirst().orElseThrow(CurrencyNotExistsException::new);
    }

}
