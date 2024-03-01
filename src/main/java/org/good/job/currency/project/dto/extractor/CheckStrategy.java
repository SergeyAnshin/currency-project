package org.good.job.currency.project.dto.extractor;

import org.good.job.currency.project.dto.Checkable;
import org.good.job.currency.project.dto.GeneralExternalApiDto;
import org.good.job.currency.project.entity.UserRequestParametersData;

import java.time.LocalDate;
import java.util.Objects;


public interface CheckStrategy {

    boolean isRateMatchParameters(GeneralExternalApiDto rate, UserRequestParametersData userRequestParameters);

    default boolean isMatchingByCurrency(Checkable rate, String currencyCode) {
        return Objects.equals(rate.getSellCurrencyCode(), currencyCode);
    }

    default boolean isMatchingUserLocalCurrency(Checkable rate, String userLocaleCurrency) {
        return Objects.equals(rate.getBuyCurrencyCode(), userLocaleCurrency);
    }

    default boolean isMatchingByDate(Checkable rate, LocalDate date) {
        return Objects.equals(rate.getDateOfRate(), date);
    }

    default boolean isMatchingByPeriod(Checkable rate, LocalDate periodStartDate, LocalDate periodEndDate) {
        var dateOfRate = rate.getDateOfRate();
        return dateOfRate.isAfter(periodStartDate.minusDays(1))
                && dateOfRate.isBefore(periodEndDate.plusDays(1));
    }

}
