package org.good.job.currency.project.dao.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dao.RateDao;
import org.good.job.currency.project.dto.ArrayDto;
import org.good.job.currency.project.dto.storage.ExternalApiDtoClassesDataStorage;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.ExternalApiCaller;
import org.good.job.currency.project.service.ExternalApiDtoMapper;
import org.good.job.currency.project.service.ExternalApiUrlService;
import org.good.job.currency.project.service.RequiredExternalApiRateExtractor;
import org.good.job.currency.project.service.mapper.RateMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@RequiredArgsConstructor

@Service
public class RestTemplateRateDao implements RateDao {

    private final ExternalApiCaller externalApiCaller;
    private final ExternalApiUrlService urlService;
    private final RateMapper rateMapper;
    private final ExternalApiDtoMapper externalApiDtoMapper;
    private final RequiredExternalApiRateExtractor requiredRateExtractor;
    private final ExternalApiDtoClassesDataStorage storage;

    @Override
    public Optional<GeneralRate> findByExternalApiNameAndCurrencyCodeAndDate(ExternalApiName externalApiName,
                                                                             String currencyCode, LocalDate date) {
        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .currency(currencyCode)
                .date(date)
                .build();

        var externalApiRateUrl = urlService.generateRateUrlByExternalApiNameAndCurrencyAndDate(userRequestParameters);
        var responseBody = externalApiCaller.call(externalApiRateUrl);

        var dtoClass = storage.getByExternalApiName(externalApiName).getRateDto();
        var externalApiDto = externalApiDtoMapper.responseBodyToExternalApiDto(responseBody, dtoClass);
        externalApiDto =
                extractRequiredExternalApiRateIfRateRepresentingJsonArray(externalApiDto, userRequestParameters);

        return Optional.of(rateMapper.externalApiRateDtoToRate(externalApiDto, userRequestParameters));
    }

    private Object extractRequiredExternalApiRateIfRateRepresentingJsonArray(Object externalApiDto,
                                                                             UserRequestParametersData userRequestParameters) {
        if (externalApiDto instanceof ArrayDto<?> rateList) {
            return requiredRateExtractor.extractFromRateList(rateList, userRequestParameters);
        }
        return externalApiDto;
    }

}
