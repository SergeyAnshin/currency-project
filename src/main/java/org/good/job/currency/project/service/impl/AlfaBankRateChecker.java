package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.dto.AlfaBankRate;
import org.good.job.currency.project.dto.GeneralExternalApiRate;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.service.RateChecker;
import org.good.job.currency.project.service.annotations.AssignedClass;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@AssignedClass(AlfaBankRate.class)

@Service
public class AlfaBankRateChecker implements RateChecker {

    @Override
    public boolean isMatchingByCurrencyAndSpecifiedDate(GeneralExternalApiRate rate, ExternalApiUrl externalApiUrl) {
        return rate.getCurrencyCode().equals(externalApiUrl.getCurrency().getCurrencyCode()) && rate.getDateOfRate()
                .equals(externalApiUrl.getDate());
    }

    @Override
    public boolean isMatchingByCurrencyAndCurrentDate(GeneralExternalApiRate rate, ExternalApiUrl externalApiUrl) {
        return rate.getCurrencyCode().equals(externalApiUrl.getCurrency().getCurrencyCode()) && rate.getDateOfRate()
                .equals(LocalDate.now());
    }

}
