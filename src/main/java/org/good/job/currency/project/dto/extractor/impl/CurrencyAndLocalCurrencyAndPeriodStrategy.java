package org.good.job.currency.project.dto.extractor.impl;

import org.good.job.currency.project.dto.extractor.CheckStrategy;
import org.good.job.currency.project.dto.Checkable;
import org.good.job.currency.project.dto.GeneralExternalApiDto;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.good.job.currency.project.entity.enums.ExternalApiName.getExternalCurrencyId;


@Component
public class CurrencyAndLocalCurrencyAndPeriodStrategy implements CheckStrategy {

    @Override
    public boolean isRateMatchParameters(GeneralExternalApiDto rate, UserRequestParametersData userRequestParameters) {
        if (rate instanceof Checkable checkable) {
            var targetCurrencyCode = userRequestParameters.getTargetCurrencyCode();
            return (isMatchingByCurrency(checkable, targetCurrencyCode)
                            || Objects.equals(checkable.getSellCurrencyCodeId(),
                                              getExternalCurrencyId(userRequestParameters.getExternalApiName(),
                                                                    targetCurrencyCode))
                    && isMatchingUserLocalCurrency(checkable, userRequestParameters.getLocalCurrencyCode())
                    && isMatchingByPeriod(checkable, userRequestParameters.getPeriodStartDate(),
                                          userRequestParameters.getPeriodEndDate()));
        } else {
            throw new IllegalArgumentException();
        }
    }

}
