package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.RateStatisticData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class StatisticServiceImplTest {

    @Autowired
    private StatisticServiceImpl statisticService;

    @Test
    void returnCorrectStatistics() {
        var date = LocalDate.of(2024, 2, 12);
        var date1 = LocalDate.of(2024, 2, 13);
        var date2 = LocalDate.of(2024, 2, 10);
        var date3 = LocalDate.of(2024, 2, 19);
        List<Object> dates = List.of(date, date1, date2, date3);

        double buyRate = 1.2;
        double buyRate1 = 5;
        double buyRate2 = 3.4;
        double buyRate3 = 9;
        double buyRateAvg = (buyRate + buyRate1 + buyRate2 + buyRate3) / 4;
        List<Object> buyRates = List.of(buyRate, buyRate1, buyRate2, buyRate3);

        double sellRate = 2.6;
        double sellRate1 = 4.1;
        double sellRate2 = 6;
        double sellRate3 = 1.5;
        double sellRateAvg = (sellRate + sellRate1 + sellRate2 + sellRate3) / 4;
        List<Object> sellRates = List.of(sellRate, sellRate1, sellRate2, sellRate3);

        List<List<Object>> buyRatesByDates = List.of(dates, buyRates);
        List<List<Object>> sellRatesByDates = List.of(dates, sellRates);

        var exStat = RateStatisticData.builder()
                .avgBuyRate(buyRateAvg)
                .avgSellRate(sellRateAvg)
                .minBuyRate(1.2)
                .minSellRate(1.5)
                .maxBuyRate(9)
                .maxSellRate(6)
                .buyRateByDate(buyRatesByDates)
                .sellRateByDate(sellRatesByDates)
                .build();

        var rate = GeneralRate.builder().buyRate(buyRate).sellRate(sellRate).date(date).build();
        var rate1 = GeneralRate.builder().buyRate(buyRate1).sellRate(sellRate1).date(date1).build();
        var rate2 = GeneralRate.builder().buyRate(buyRate2).sellRate(sellRate2).date(date2).build();
        var rate3 = GeneralRate.builder().buyRate(buyRate3).sellRate(sellRate3).date(date3).build();
        var rates = List.of(rate, rate1, rate2, rate3);

        assertEquals(exStat, statisticService.getStatistics(rates));
    }

}