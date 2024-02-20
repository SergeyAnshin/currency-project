package org.good.job.currency.project.dao.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.config.ExternalApiConfigurationProperty;
import org.good.job.currency.project.dao.RateDao;
import org.good.job.currency.project.dto.ArrayDto;
import org.good.job.currency.project.dto.extractor.CheckStrategy;
import org.good.job.currency.project.dto.extractor.RequiredExternalApiDtoExtractor;
import org.good.job.currency.project.dto.extractor.impl.CurrencyAndLocalCurrencyAndDateStrategy;
import org.good.job.currency.project.dto.extractor.impl.CurrencyAndLocalCurrencyAndPeriodStrategy;
import org.good.job.currency.project.dto.storage.ExternalApiDtoClassesDataStorage;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.service.ExternalApiCaller;
import org.good.job.currency.project.service.ExternalApiDtoMapper;
import org.good.job.currency.project.service.ExternalApiUrlService;
import org.good.job.currency.project.service.mapper.RateMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor

@Service
public class RestTemplateRateDao implements RateDao {

    private final ExternalApiConfigurationProperty externalApiConfigurationProperty;
    private final ExternalApiUrlService externalApiUrlService;
    private final ExternalApiCaller externalApiCaller;
    private final ExternalApiDtoMapper externalApiDtoMapper;
    private final RateMapper rateMapper;
    private final RequiredExternalApiDtoExtractor requiredRateExtractor;
    private final ExternalApiDtoClassesDataStorage externalApiDtoClassesDataStorage;
    private final CurrencyAndLocalCurrencyAndPeriodStrategy currencyAndPeriodStrategy;
    private final CurrencyAndLocalCurrencyAndDateStrategy currencyAndDateStrategy;

    @Override
    public Optional<GeneralRate> findByExternalApiNameAndCurrencyCodeAndDate(
            UserRequestParametersData userRequestParameters) {
        var externalApiName = userRequestParameters.getExternalApiName();
        var rateExternalApiUrl = externalApiConfigurationProperty.getExternalApiConfigMap()
                .get(externalApiName.name())
                .getRateExternalApiUrl();
        var externalApiDtoClass = externalApiDtoClassesDataStorage.getByExternalApiName(externalApiName).getRateDto();
        var externalApiDto = getDataFromExternalApi(userRequestParameters, rateExternalApiUrl, externalApiDtoClass);
        var requiredRates = extractRequiredRates(externalApiDto, userRequestParameters, currencyAndDateStrategy);
        return Optional.of(rateMapper.externalApiRateDtoToRate(requiredRates.getFirst()));
    }

    @Override
    public List<GeneralRate> findByExternalApiNameAndCurrencyCodeAndPeriod(
            UserRequestParametersData userRequestParameters) {
        var externalApiName = userRequestParameters.getExternalApiName();
        var rateExternalApiUrl = externalApiConfigurationProperty.getExternalApiConfigMap()
                .get(externalApiName.name())
                .getRatesByPeriodExternalApiUrl();
        var externalApiDtoClass =
                externalApiDtoClassesDataStorage.getByExternalApiName(externalApiName).getRatesByPeriodDto();
        var externalApiDtoList = getDataFromExternalApi(userRequestParameters, rateExternalApiUrl, externalApiDtoClass);
        var requiredRates = extractRequiredRates(externalApiDtoList, userRequestParameters, currencyAndPeriodStrategy);
        return rateMapper.externalApiRateListToRateList(requiredRates);
    }

    private Object getDataFromExternalApi(UserRequestParametersData userRequestParameters, String externalApiUrl,
                                          Class<?> externalApiDtoClass) {
        var externalApiUrlWithParameters =
                externalApiUrlService.substituteParametersInUrl(userRequestParameters, externalApiUrl);
        var responseBody = externalApiCaller.call(externalApiUrlWithParameters);
        return externalApiDtoMapper.responseBodyToExternalApiDto(responseBody, externalApiDtoClass);
    }

    private ArrayDeque<Object> extractRequiredRates(Object externalApiDto,
                                                    UserRequestParametersData userRequestParameters,
                                                    CheckStrategy checkStrategy) {
        if (externalApiDto instanceof ArrayDto<?> rateList) {
            return requiredRateExtractor.extract(rateList, userRequestParameters, checkStrategy);
        }
        return new ArrayDeque<>(Collections.singleton(externalApiDto));
    }

}
