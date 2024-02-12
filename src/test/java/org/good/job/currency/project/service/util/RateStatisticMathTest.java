package org.good.job.currency.project.service.util;

import org.good.job.currency.project.entity.GeneralRate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class RateStatisticMathTest {

    private final List<GeneralRate> rates;
    private final double expectedAvgBuyRate;
    private final double expectedAvgSellRate;
    private final double expectedMinBuyRate;
    private final double expectedMinSellRate;
    private final double expectedMaxBuyRate;
    private final double expectedMaxSellRate;
    private final List<List<Object>> buyRatesByDate;
    private final List<List<Object>> sellRatesByDate;

    RateStatisticMathTest() {
        var buyRate = 5.1;
        var buyRate1 = 4.0;
        var buyRate2 = 8.6;
        var buyRate3 = 4.7;
        var buyRate4 = 2.0;

        var sellRate = 6.3;
        var sellRate1 = 4.9;
        var sellRate2 = 7.5;
        var sellRate3 = 1.1;
        var sellRate4 = 8.0;

        var date = LocalDate.of(2024, 2, 1);
        var date1 = LocalDate.of(2024, 2, 19);
        var date2 = LocalDate.of(2024, 2, 7);
        var date3 = LocalDate.of(2024, 2, 9);
        var date4 = LocalDate.of(2024, 2, 21);

        var generalRate = GeneralRate.builder().buyRate(buyRate).sellRate(sellRate).date(date).build();
        var generalRate1 = GeneralRate.builder().buyRate(buyRate1).sellRate(sellRate1).date(date1).build();
        var generalRate2 = GeneralRate.builder().buyRate(buyRate2).sellRate(sellRate2).date(date2).build();
        var generalRate3 = GeneralRate.builder().buyRate(buyRate3).sellRate(sellRate3).date(date3).build();
        var generalRate4 = GeneralRate.builder().buyRate(buyRate4).sellRate(sellRate4).date(date4).build();

        rates = List.of(generalRate, generalRate1, generalRate2, generalRate3, generalRate4);

        expectedAvgBuyRate = (buyRate + buyRate1 + buyRate2 + buyRate3 + buyRate4) / 5;
        expectedAvgSellRate = (sellRate + sellRate1 + sellRate2 + sellRate3 + sellRate4) / 5;

        expectedMinBuyRate = buyRate4;
        expectedMinSellRate = sellRate3;

        expectedMaxBuyRate = buyRate2;
        expectedMaxSellRate = sellRate4;

        buyRatesByDate = List.of(List.of(date, date1, date2, date3, date4),
                                 List.of(buyRate, buyRate1, buyRate2, buyRate3, buyRate4));
        sellRatesByDate = List.of(List.of(date, date1, date2, date3, date4),
                                  List.of(sellRate, sellRate1, sellRate2, sellRate3, sellRate4));
    }

    @Test
    void getAvgRate() {
        assertAll(() -> {
            assertEquals(expectedAvgBuyRate, RateStatisticMath.getAvgRate(rates, GeneralRate::getBuyRate));
            assertEquals(expectedAvgSellRate, RateStatisticMath.getAvgRate(rates, GeneralRate::getSellRate));
        });
    }

    @Test
    void getMinRate() {
        assertAll(() -> {
            assertEquals(expectedMinBuyRate, RateStatisticMath.getMinRate(rates, GeneralRate::getBuyRate));
            assertEquals(expectedMinSellRate, RateStatisticMath.getMinRate(rates, GeneralRate::getSellRate));
        });
    }

    @Test
    void getMaxRate() {
        assertAll(() -> {
            assertEquals(expectedMaxBuyRate, RateStatisticMath.getMaxRate(rates, GeneralRate::getBuyRate));
            assertEquals(expectedMaxSellRate, RateStatisticMath.getMaxRate(rates, GeneralRate::getSellRate));
        });
    }

    @Test
    void getRateByDateArray() {
        assertAll(() -> {
            assertEquals(buyRatesByDate, RateStatisticMath.getRateByDateArray(rates, GeneralRate::getBuyRate));
            assertEquals(sellRatesByDate, RateStatisticMath.getRateByDateArray(rates, GeneralRate::getSellRate));
        });
    }

}