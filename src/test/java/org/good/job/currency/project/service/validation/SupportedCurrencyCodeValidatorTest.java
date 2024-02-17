package org.good.job.currency.project.service.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class SupportedCurrencyCodeValidatorTest {

    @Autowired
    private SupportedCurrencyCodeValidator currencyCodeValidator;

    @Test
    void returnTrueIfGetISO4217Currency() {
        assertAll(() -> {
            assertTrue(currencyCodeValidator.isValid("USD", null));
            assertTrue(currencyCodeValidator.isValid("EUR", null));
            assertTrue(currencyCodeValidator.isValid("RUB", null));
            assertTrue(currencyCodeValidator.isValid("BYN", null));
        });
    }

    @Test
    void returnFalseIfCurrencyIncorrect() {
        assertAll(() -> {
            assertTrue(currencyCodeValidator.isValid("als", null));
            assertTrue(currencyCodeValidator.isValid("GKK", null));
            assertTrue(currencyCodeValidator.isValid("1", null));
            assertTrue(currencyCodeValidator.isValid("adal11", null));
        });
    }

}