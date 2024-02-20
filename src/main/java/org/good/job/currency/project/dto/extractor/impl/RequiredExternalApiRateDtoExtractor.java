package org.good.job.currency.project.dto.extractor.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dto.ArrayDto;
import org.good.job.currency.project.dto.extractor.CheckStrategy;
import org.good.job.currency.project.dto.extractor.RequiredExternalApiDtoExtractor;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.service.exception.RateNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;


@RequiredArgsConstructor

@Service
public class RequiredExternalApiRateDtoExtractor implements RequiredExternalApiDtoExtractor {

    @Override
    public ArrayDeque<Object> extract(ArrayDto<?> externalApiRateDto, UserRequestParametersData userRequestParameters,
                                      CheckStrategy checkStrategy) {
        var ratesMatchingSpecifiedParameters = new ArrayDeque<>();
        for (var rate : externalApiRateDto.getListDto()) {
            if (checkStrategy.isRateMatchParameters(rate, userRequestParameters)) {
                ratesMatchingSpecifiedParameters.addFirst(rate);
            }
        }
        if (ratesMatchingSpecifiedParameters.isEmpty()) {
            throw new RateNotFoundException();
        }
        return ratesMatchingSpecifiedParameters;
    }

}
