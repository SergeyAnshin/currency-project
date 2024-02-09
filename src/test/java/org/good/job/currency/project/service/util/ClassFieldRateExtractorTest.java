package org.good.job.currency.project.service.util;

import org.good.job.currency.project.dto.BelarusBankRate;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class ClassFieldRateExtractorTest {

    public static final double BUY_USD = 3.185;
    public static final double SELL_USD = 3.245;
    public static final double BUY_EUR = 3.4;
    public static final double SELL_EUR = 3.49;
    public static final double BUY_RUB = 3.47;
    public static final double SELL_RUB = 3.54;
    public static final double BUY_CNY = 4.42;
    public static final double SELL_CNY = 4.54;
    private final BelarusBankRate belarusBankRateExternalApiDto = BelarusBankRate.builder()
            .date(LocalDateTime.of(2024, 2, 9, 16, 30))
            .buyUsd(BUY_USD)
            .sellUsd(SELL_USD)
            .buyEur(BUY_EUR)
            .sellEur(SELL_EUR)
            .buyRub(BUY_RUB)
            .sellRub(SELL_RUB)
            .buyCny(BUY_CNY)
            .sellCny(SELL_CNY)
            .buyConversionUsdEur(0.92)
            .sellConversionUsdEur(1.065)
            .buyConversionUsdRub(90.30)
            .sellConversionUsdRub(0.0108)
            .sellConversionRubEur(96.5000)
            .buyConversionRubEur(0.0100)
            .buyConversionCnyUsd(0.1365)
            .sellConversionCnyUsd(7.0155)
            .buyConversionCnyEur(0.1270)
            .sellConversionCnyEur(7.4890)
            .buyConversionCnyRub(12.4860)
            .sellConversionCnyRub(0.0765)
            .build();

    @Test
    void getBuyRateByCurrencyCodeReturnCorrectValuesForDifferentCurrencies() {
        assertAll(() -> {
            assertEquals(BUY_USD,
                         ClassFieldRateExtractor.getBuyRateByCurrencyCode(belarusBankRateExternalApiDto,
                                                                          "USD"));
            assertEquals(BUY_EUR,
                         ClassFieldRateExtractor.getBuyRateByCurrencyCode(belarusBankRateExternalApiDto,
                                                                          "EUR"));
            assertEquals(BUY_RUB,
                         ClassFieldRateExtractor.getBuyRateByCurrencyCode(belarusBankRateExternalApiDto,
                                                                          "RUB"));
            assertEquals(BUY_CNY,
                         ClassFieldRateExtractor.getBuyRateByCurrencyCode(belarusBankRateExternalApiDto,
                                                                          "CNY"));
        });
    }

    @Test
    void getSellRateByCurrencyCodeReturnCorrectValuesForDifferentCurrencies() {
        assertAll(() -> {
            assertEquals(SELL_USD,
                         ClassFieldRateExtractor.getSellRateByCurrencyCode(belarusBankRateExternalApiDto,
                                                                           "USD"));
            assertEquals(SELL_EUR,
                         ClassFieldRateExtractor.getSellRateByCurrencyCode(belarusBankRateExternalApiDto,
                                                                           "EUR"));
            assertEquals(SELL_RUB,
                         ClassFieldRateExtractor.getSellRateByCurrencyCode(belarusBankRateExternalApiDto,
                                                                           "RUB"));
            assertEquals(SELL_CNY,
                         ClassFieldRateExtractor.getSellRateByCurrencyCode(belarusBankRateExternalApiDto,
                                                                           "CNY"));
        });
    }

}