package org.good.job.currency.project.entity;

import lombok.*;

import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class RateStatisticData {

    public double avgBuyRate;
    public double avgSellRate;
    public double minBuyRate;
    public double minSellRate;
    public double maxBuyRate;
    public double maxSellRate;
    public List<List<Object>> buyRateByDate;
    public List<List<Object>> sellRateByDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateStatisticData that = (RateStatisticData) o;
        return Double.compare(avgBuyRate, that.avgBuyRate) == 0
                && Double.compare(avgSellRate, that.avgSellRate) == 0
                && Double.compare(minBuyRate, that.minBuyRate) == 0
                && Double.compare(minSellRate, that.minSellRate) == 0
                && Double.compare(maxBuyRate, that.maxBuyRate) == 0
                && Double.compare(maxSellRate, that.maxSellRate) == 0 && Objects.equals(buyRateByDate,
                                                                                        that.buyRateByDate)
                && Objects.equals(sellRateByDate, that.sellRateByDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(avgBuyRate, avgSellRate, minBuyRate, minSellRate, maxBuyRate, maxSellRate, buyRateByDate,
                            sellRateByDate);
    }

}
