package org.good.job.currency.project.service.util;

import org.good.job.currency.project.entity.GeneralRate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;


public class RateStatisticMath {

    public static double getAvgRate(List<GeneralRate> generalRates,
                                    Function<GeneralRate, Double> requiredRateTypeGetterFunction) {
        if (nonNull(generalRates) && !generalRates.isEmpty() && nonNull(requiredRateTypeGetterFunction)) {
            return generalRates.stream()
                    .map(requiredRateTypeGetterFunction)
                    .collect(Collectors.averagingDouble(Double::valueOf));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double getMinRate(List<GeneralRate> generalRates,
                                    Function<GeneralRate, Double> requiredRateTypeGetterFunction) {
        if (nonNull(generalRates) && !generalRates.isEmpty() && nonNull(requiredRateTypeGetterFunction)) {
            return generalRates.stream().map(requiredRateTypeGetterFunction).min(Double::compare).orElseThrow();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double getMaxRate(List<GeneralRate> generalRates,
                                    Function<GeneralRate, Double> requiredRateTypeGetterFunction) {
        if (nonNull(generalRates) && !generalRates.isEmpty() && nonNull(requiredRateTypeGetterFunction)) {
            return generalRates.stream().map(requiredRateTypeGetterFunction).max(Double::compare).orElseThrow();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static List<List<Object>> getRateByDateArray(List<GeneralRate> generalRates,
                                                        Function<GeneralRate, Double> requiredRateTypeGetterFunction) {
        if (nonNull(generalRates) && !generalRates.isEmpty() && nonNull(requiredRateTypeGetterFunction)) {
            var dates = new ArrayList<>();
            var rates = new ArrayList<>();
            List<List<Object>> ratesByDates = new ArrayList<>();
            ratesByDates.add(0, dates);
            ratesByDates.add(1, rates);
            for (GeneralRate generalRate : generalRates) {
                ratesByDates.get(0).add(generalRate.getDate());
                ratesByDates.get(1).add(requiredRateTypeGetterFunction.apply(generalRate));
            }
            return ratesByDates;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
