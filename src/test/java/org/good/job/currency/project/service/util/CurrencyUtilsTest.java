package org.good.job.currency.project.service.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CurrencyUtilsTest {

    @Test
    void isSupportedCurrencyReturnTrueIfCurrencyIsSupported() {
        var usdCurrencyCode = "USD";
        var eurCurrencyCode = "EUR";
        var rubCurrencyCode = "RUB";
        assertAll(() -> {
            assertTrue(CurrencyUtils.isSupportedCurrency(usdCurrencyCode));
            assertTrue(CurrencyUtils.isSupportedCurrency(eurCurrencyCode));
            assertTrue(CurrencyUtils.isSupportedCurrency(rubCurrencyCode));
        });
    }

    @Test
    void isSupportedCurrencyReturnFalseIfCurrencyNotSupported() {
        var notSupportedCurrencyCode = "lsda";
        assertAll(() -> {
            assertFalse(CurrencyUtils.isSupportedCurrency(notSupportedCurrencyCode));
        });
    }

}