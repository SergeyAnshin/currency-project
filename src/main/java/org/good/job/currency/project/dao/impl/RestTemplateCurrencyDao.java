package org.good.job.currency.project.dao.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.config.ExternalApiConfigurationProperty;
import org.good.job.currency.project.dao.CurrencyDao;
import org.good.job.currency.project.dto.storage.ExternalApiDtoClassesDataStorage;
import org.good.job.currency.project.entity.GeneralCurrency;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.ExternalApiCaller;
import org.good.job.currency.project.service.ExternalApiDtoMapper;
import org.good.job.currency.project.service.ExternalApiUrlService;
import org.good.job.currency.project.service.mapper.CurrencyMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


@RequiredArgsConstructor

@Service
public class RestTemplateCurrencyDao implements CurrencyDao {

    private final ExternalApiConfigurationProperty externalApiConfigurationProperty;
    private final ExternalApiUrlService externalApiUrlService;
    private final ExternalApiCaller externalApiCaller;
    private final ExternalApiDtoMapper externalApiDtoMapper;
    private final ExternalApiDtoClassesDataStorage externalApiDtoClassesDataStorage;
    private final CurrencyMapper currencyMapper;

    @Override
    public List<GeneralCurrency> findByExternalApiName(ExternalApiName externalApiName) {

        var userRequestParameters = UserRequestParametersData.builder().externalApiName(externalApiName).build();
        var currencyExternalApiUrl = externalApiConfigurationProperty.getExternalApiConfigMap()
                .get(externalApiName.name())
                .getCurrencyExternalApiUrl();
        var currencyExternalApiUrlWithParameters =
                externalApiUrlService.substituteParametersInUrl(userRequestParameters, currencyExternalApiUrl);
        var responseBody = externalApiCaller.call(currencyExternalApiUrlWithParameters);
        var dtoClass = externalApiDtoClassesDataStorage.getByExternalApiName(externalApiName).getCurrencyDto();
        Object externalApiDto = externalApiDtoMapper.responseBodyToExternalApiDto(responseBody, dtoClass);
        TreeSet<GeneralCurrency> sortedGeneralCurrencies = new TreeSet<>(
                currencyMapper.currencyDtoListToCurrencyList(externalApiDto).getDtoList());
        return new ArrayList<>(sortedGeneralCurrencies);
    }

}