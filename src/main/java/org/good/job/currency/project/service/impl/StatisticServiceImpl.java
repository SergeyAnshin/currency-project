package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.RateStatisticData;
import org.good.job.currency.project.service.StatisticService;
import org.good.job.currency.project.service.util.RateStatisticMath;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatisticServiceImpl implements StatisticService {


    @Override
    public RateStatisticData getStatistics(List<GeneralRate> generalRates) {
        var avgBuyRate = RateStatisticMath.getAvgRate(generalRates, GeneralRate::getBuyRate);
        var avgSellRate = RateStatisticMath.getAvgRate(generalRates, GeneralRate::getSellRate);

        var minBuyRate = RateStatisticMath.getMinRate(generalRates, GeneralRate::getBuyRate);
        var minSellRate = RateStatisticMath.getMinRate(generalRates, GeneralRate::getSellRate);

        var maxBuyRate = RateStatisticMath.getMaxRate(generalRates, GeneralRate::getBuyRate);
        var maxSellRate = RateStatisticMath.getMaxRate(generalRates, GeneralRate::getSellRate);

        var buyRateByDate = RateStatisticMath.getRateByDateArray(generalRates, GeneralRate::getBuyRate);
        var sellRateByDate = RateStatisticMath.getRateByDateArray(generalRates, GeneralRate::getSellRate);

        return RateStatisticData.builder()
                .avgBuyRate(avgBuyRate)
                .avgSellRate(avgSellRate)
                .minBuyRate(minBuyRate)
                .minSellRate(minSellRate)
                .maxBuyRate(maxBuyRate)
                .maxSellRate(maxSellRate)
                .buyRateByDate(buyRateByDate)
                .sellRateByDate(sellRateByDate)
                .build();
    }

}
