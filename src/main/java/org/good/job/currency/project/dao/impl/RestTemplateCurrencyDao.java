package org.good.job.currency.project.dao.impl;

import lombok.RequiredArgsConstructor;
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

    private final ExternalApiCaller externalApiCaller;
    private final ExternalApiUrlService urlService;
    private final CurrencyMapper currencyMapper;
    private final ExternalApiDtoMapper externalApiDtoMapper;
    private final ExternalApiDtoClassesDataStorage storage;

    @Override
    public List<GeneralCurrency> findByExternalApiName(ExternalApiName externalApiName) {
        var param = UserRequestParametersData.builder().externalApiName(externalApiName).build();
        var externalApiRateUrl = urlService.generateCurrencyUrlByExternalApiName(param);
        var responseBody = externalApiCaller.call(externalApiRateUrl);
        var dtoClass = storage.getByExternalApiName(externalApiName).getCurrencyDto();
        var externalApiDto = externalApiDtoMapper.responseBodyToExternalApiDto(responseBody, dtoClass);
        var sortedGeneralCurrencies =
                new TreeSet<>(currencyMapper.currencyDtoListToCurrencyList(externalApiDto).getDtoList());
        return new ArrayList<>(sortedGeneralCurrencies);
    }


}
