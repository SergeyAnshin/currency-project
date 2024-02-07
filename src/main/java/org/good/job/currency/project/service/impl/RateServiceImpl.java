package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dto.Foreachable;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.*;
import org.good.job.currency.project.service.mapper.RateMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Currency;


@RequiredArgsConstructor

@Service
public class RateServiceImpl implements RateService {

    private final ExternalApiCaller externalApiCaller;
    private final ExternalApiUrlService urlService;
    private final RateMapper rateMapper;
    private final ExternalApiDtoMapper externalApiDtoMapper;
    private final ArrayResolver rateArrayResolver;

    //TODO Отрефакторить
    @Override
    public GeneralRate getRateByExternalApiNameAndCurrencyAndDate(ExternalApiName externalApiName,
                                                                  Currency currencyCode,
                                                                  LocalDate date) {
        var param = ExternalApiUrl.builder().externalApiName(externalApiName).currency(currencyCode).date(date).build();

        var externalApiRateUrl = urlService.generateRateUrlByExternalApiNameAndCurrencyAndDate(param);
        var responseBody = externalApiCaller.call(externalApiRateUrl);

        var dtoClass = externalApiName.getExternalApiRateProperty().getRateProperty().getDtoClass();
        var externalApiDto = externalApiDtoMapper.responseBodyToExternalApiDto(responseBody, dtoClass);
        if (externalApiDto instanceof Foreachable) {
            externalApiDto = rateArrayResolver.resolve(externalApiDto, param);
        }
        return rateMapper.externalApiRateDtoToRate(externalApiDto);
    }

}
