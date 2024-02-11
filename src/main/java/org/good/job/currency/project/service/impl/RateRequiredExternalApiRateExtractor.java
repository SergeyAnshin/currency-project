package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dto.ArrayRate;
import org.good.job.currency.project.entity.ExternalApiUrlOld;
import org.good.job.currency.project.service.RequiredExternalApiRateExtractor;
import org.good.job.currency.project.service.exception.RateNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;


@RequiredArgsConstructor

@Service
public class RateRequiredExternalApiRateExtractor implements RequiredExternalApiRateExtractor {

    private final RateCheckFactory rateCheckFactory;

    @Override
    public Object extractFromRateList(ArrayRate<?> externalApiRateDto, ExternalApiUrlOld externalApiUrlOld) {
        var ratesMatchingSpecifiedParameters = new ArrayDeque<>();
        for (var rate : externalApiRateDto.getListDto()) {
            var rateChecker = rateCheckFactory.getRateCheckerByRateDtoClass(rate.getClass());
            if (rateChecker.isRateMatchParameters(rate, externalApiUrlOld)) {
                ratesMatchingSpecifiedParameters.addFirst(rate);
            }
        }
        if (ratesMatchingSpecifiedParameters.isEmpty()) {
            throw new RateNotFoundException();
        }
        return ratesMatchingSpecifiedParameters.getFirst();
    }

}
