package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.dto.AlfaBankDto;
import org.good.job.currency.project.dto.Checkable;
import org.good.job.currency.project.dto.GeneralExternalApiDto;
import org.good.job.currency.project.dto.enums.ConstCurrency;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.service.RateChecker;
import org.good.job.currency.project.service.annotation.AssignedClass;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.Objects;


@AssignedClass(AlfaBankDto.class)

@Service
public class AlfaBankRateChecker implements RateChecker {

    // TODO если изменится локальная переменная ?
    @Override
    public boolean isRateMatchParameters(GeneralExternalApiDto externalApiRate,
                                         UserRequestParametersData userRequestParameters) {
        if (externalApiRate instanceof Checkable rate) {
            return isMatchingByCurrency(rate, userRequestParameters.getTargetCurrencyCode())
                    && isMatchingByDate(rate, userRequestParameters.getDate())
                    && isMatchingUserLocalCurrency(rate, userRequestParameters.getLocalCurrencyCode());
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isMatchingUserLocalCurrency(Checkable rate, String userLocaleCurrency) {
        return Objects.equals(rate.getBuyCurrencyCode(), userLocaleCurrency);
    }

}
