package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dto.ArrayRate;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.service.ArrayResolver;
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
                if (rateChecker.isMatchingByCurrencyAndSpecifiedDate(rate, externalApiUrl)) {
                    ratesMatchingSpecifiedParameters.addFirst(rate);
                    break;
                } else if (rateChecker.isMatchingByCurrencyAndCurrentDate(rate, externalApiUrl)) {
                    ratesMatchingSpecifiedParameters.addLast(rate);
                }
            }
            if (ratesMatchingSpecifiedParameters.isEmpty()) {
                throw new RuntimeException();
            }
            return ratesMatchingSpecifiedParameters.getFirst();
        }
        return externalApiRateDto;
    }


}
