package org.good.job.currency.project.service;

import org.good.job.currency.project.dto.Checkable;
import org.good.job.currency.project.dto.GeneralExternalApiDto;
import org.good.job.currency.project.entity.ExternalApiUrl;

import java.time.LocalDate;
import java.util.Objects;


public interface RateChecker {

    boolean isRateMatchParameters(GeneralExternalApiDto rate, ExternalApiUrl externalApiUrl);

    default boolean isMatchingByDate(Checkable rate, LocalDate date) {
        return rate.getDateOfRate().equals(date);
    }

    default boolean isMatchingByCurrency(Checkable rate, String currencyCode) {
        return Objects.equals(rate.getSellCurrencyCode(), currencyCode);
    }

}
