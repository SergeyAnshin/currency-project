package org.good.job.currency.project.dao.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dao.RateDao;
import org.good.job.currency.project.dto.ArrayRate;
import org.good.job.currency.project.entity.ExternalApiUrlOld;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.ExternalApiCaller;
import org.good.job.currency.project.service.ExternalApiDtoMapper;
import org.good.job.currency.project.service.ExternalApiUrlService;
import org.good.job.currency.project.service.RequiredExternalApiRateExtractor;
import org.good.job.currency.project.service.mapper.RateMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;


@RequiredArgsConstructor

@Service
public class RestTemplateRateDao implements RateDao {

    private final ExternalApiCaller externalApiCaller;
    private final ExternalApiUrlService urlService;
    private final RateMapper rateMapper;
    private final ExternalApiDtoMapper externalApiDtoMapper;
    private final RequiredExternalApiRateExtractor requiredRateExtractor;

    @Override
    public Optional<GeneralRate> findByExternalApiNameAndCurrencyCodeAndDate(ExternalApiName externalApiName,
                                                                             Currency currencyCode, LocalDate date) {
        var param = ExternalApiUrlOld.builder().externalApiName(externalApiName).currency(currencyCode).date(date).build();

        var externalApiRateUrl = urlService.generateRateUrlByExternalApiNameAndCurrencyAndDate(param);
        var responseBody = externalApiCaller.call(externalApiRateUrl);

        var dtoClass = externalApiName.getExternalApiRateProperty().getRateProperty().getDtoClass();
        var externalApiDto = externalApiDtoMapper.responseBodyToExternalApiDto(responseBody, dtoClass);
        externalApiDto = extractRequiredExternalApiRateIfRateRepresentingJsonArray(externalApiDto, param);

        return Optional.of(rateMapper.externalApiRateDtoToRate(externalApiDto, param));
    }

    private Object extractRequiredExternalApiRateIfRateRepresentingJsonArray(Object externalApiDto, ExternalApiUrlOld param) {
        if (externalApiDto instanceof ArrayRate<?> rateList) {
            return requiredRateExtractor.extractFromRateList(rateList, param);
        }
        return externalApiDto;
    }

}
