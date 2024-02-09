package org.good.job.currency.project.service.util;

import org.good.job.currency.project.service.exception.CurrencyNotExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StringToCurrencyConverterTest {

    public static final String USD_CORRECT_CURRENCY_CODE = "USD";
    public static final String EUR_CORRECT_CURRENCY_CODE = "EUR";
    public static final String RUB_CORRECT_CURRENCY_CODE = "RUB";
    public static final String CNY_CORRECT_CURRENCY_CODE = "CNY";
    public static final String INCORRECT_CURRENCY_CODE = "FFF";
    @Autowired
    private StringToCurrencyConverter currencyConverter;

    @Test
    void convertReturnCurrencyIfCurrencyCodeCorrect() {
        assertAll(() -> {
            assertEquals(Currency.getInstance(USD_CORRECT_CURRENCY_CODE),
                         currencyConverter.convert(USD_CORRECT_CURRENCY_CODE));
            assertEquals(Currency.getInstance(EUR_CORRECT_CURRENCY_CODE),
                         currencyConverter.convert(EUR_CORRECT_CURRENCY_CODE));
            assertEquals(Currency.getInstance(RUB_CORRECT_CURRENCY_CODE),
                         currencyConverter.convert(RUB_CORRECT_CURRENCY_CODE));
            assertEquals(Currency.getInstance(CNY_CORRECT_CURRENCY_CODE),
                         currencyConverter.convert(CNY_CORRECT_CURRENCY_CODE));
        });
    }

    @Test
    void convertThrowExceptionIfCurrencyCodeInCorrect() {
        assertThrows(CurrencyNotExistsException.class, () -> currencyConverter.convert(INCORRECT_CURRENCY_CODE));
    }

}