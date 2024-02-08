package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.dto.AlfaBankRate;
import org.good.job.currency.project.dto.Checkable;
import org.good.job.currency.project.dto.GeneralExternalApiRate;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.service.RateChecker;
import org.good.job.currency.project.service.annotations.AssignedClass;
import org.springframework.stereotype.Service;

import java.util.Currency;


@AssignedClass(AlfaBankRate.class)

@Service
public class AlfaBankRateChecker implements RateChecker {

    @Override
    public boolean isRateMatchParameters(GeneralExternalApiRate externalApiRate, ExternalApiUrl externalApiUrl) {
        if (externalApiRate instanceof Checkable rate) {
            return isMatchingByCurrency(rate, externalApiUrl.getCurrency())
                    && isMatchingByDate(rate, externalApiUrl.getDate())
                    && isMatchingUserLocalCurrency(rate, rate.getLocalCurrency());
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isMatchingUserLocalCurrency(Checkable rate, Currency userLocaleCurrency) {
        return rate.getBuyCurrencyCode().equals(userLocaleCurrency.getCurrencyCode());
    }

}
