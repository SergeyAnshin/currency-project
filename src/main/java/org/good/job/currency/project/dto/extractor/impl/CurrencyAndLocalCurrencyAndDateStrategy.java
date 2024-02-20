package org.good.job.currency.project.dto.extractor.impl;

import org.good.job.currency.project.dto.Checkable;
import org.good.job.currency.project.dto.GeneralExternalApiDto;
import org.good.job.currency.project.dto.extractor.CheckStrategy;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.springframework.stereotype.Component;


@Component
public class CurrencyAndLocalCurrencyAndDateStrategy implements CheckStrategy {

    @Override
    public boolean isRateMatchParameters(GeneralExternalApiDto rate, UserRequestParametersData userRequestParameters) {
        if (rate instanceof Checkable checkable) {
            return isMatchingByCurrency(checkable, userRequestParameters.getTargetCurrencyCode())
                    && isMatchingUserLocalCurrency(checkable, userRequestParameters.getLocalCurrencyCode())
                    && isMatchingByDate(checkable, userRequestParameters.getDate());
        } else {
            throw new IllegalArgumentException();
        }
    }

}
