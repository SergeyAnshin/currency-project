package org.good.job.currency.project.service.validation;

import org.good.job.currency.project.service.validation.impl.SupportedCurrencyCodeValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


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
            assertFalse(currencyCodeValidator.isValid("als", null));
            assertFalse(currencyCodeValidator.isValid("GKK", null));
            assertFalse(currencyCodeValidator.isValid("1", null));
            assertFalse(currencyCodeValidator.isValid("adal11", null));
        });
    }

}