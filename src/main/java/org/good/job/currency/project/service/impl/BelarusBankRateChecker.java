package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.dto.BelarusBankConvertedDto;
import org.good.job.currency.project.dto.Checkable;
import org.good.job.currency.project.dto.GeneralExternalApiRate;
import org.good.job.currency.project.entity.ExternalApiUrlOld;
import org.good.job.currency.project.service.RateChecker;
import org.good.job.currency.project.service.annotations.AssignedClass;
import org.springframework.stereotype.Service;


@AssignedClass(BelarusBankConvertedDto.class)

@Service
public class BelarusBankRateChecker implements RateChecker {

    @Override
    public boolean isRateMatchParameters(GeneralExternalApiRate externalApiRate, ExternalApiUrlOld externalApiUrlOld) {
        if (externalApiRate instanceof Checkable rate) {
            return isMatchingByCurrency(rate, externalApiUrlOld.getCurrency())
                    && isMatchingByDate(rate, externalApiUrlOld.getDate());
        } else {
            throw new IllegalArgumentException();
        }
    }

}
