package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.dto.BelarusBankConvertedDto;
import org.good.job.currency.project.dto.Checkable;
import org.good.job.currency.project.dto.GeneralExternalApiDto;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.service.RateChecker;
import org.good.job.currency.project.service.annotation.AssignedClass;
import org.springframework.stereotype.Service;


@AssignedClass(BelarusBankConvertedDto.class)

@Service
public class BelarusBankRateChecker implements RateChecker {

    @Override
    public boolean isRateMatchParameters(GeneralExternalApiDto externalApiRate, ExternalApiUrl externalApiUrl) {
        if (externalApiRate instanceof Checkable rate) {
            return isMatchingByCurrency(rate, externalApiUrl.getCurrency())
                    && isMatchingByDate(rate, externalApiUrl.getDate());
        } else {
            throw new IllegalArgumentException();
        }
    }

}
