package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dto.ArrayRate;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.service.ArrayResolver;
import org.good.job.currency.project.service.exception.RateNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;


@RequiredArgsConstructor

@Service
public class RateArrayByCurrencyAndDateResolver implements ArrayResolver {

    private final RateCheckFactory rateCheckFactory;

    public Object resolve(Object externalApiRateDto, ExternalApiUrl externalApiUrl) {
        if (externalApiRateDto instanceof ArrayRate<?> arrayRate) {
            var ratesMatchingSpecifiedParameters = new ArrayDeque<>();
            for (var rate : arrayRate.getListDto()) {
                var rateChecker = rateCheckFactory.getRateCheckerByRateDtoClass(rate.getClass());
                if (rateChecker.isRateMatchParameters(rate, externalApiUrl)) {
                    ratesMatchingSpecifiedParameters.addFirst(rate);
                }
            }
            if (ratesMatchingSpecifiedParameters.isEmpty()) {
                throw new RateNotFoundException();
            }
            return ratesMatchingSpecifiedParameters.getFirst();
        }
        return externalApiRateDto;
    }

}
