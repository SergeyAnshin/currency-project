package org.good.job.currency.project.dao.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dao.CurrencyDao;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.entity.GeneralCurrency;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.ExternalApiCaller;
import org.good.job.currency.project.service.ExternalApiDtoMapper;
import org.good.job.currency.project.service.ExternalApiUrlService;
import org.good.job.currency.project.service.mapper.CurrencyMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.TreeSet;


@RequiredArgsConstructor

@Service
public class RestTemplateCurrencyDao implements CurrencyDao {

    private final ExternalApiCaller externalApiCaller;
    private final ExternalApiUrlService urlService;
    private final CurrencyMapper currencyMapper;
    private final ExternalApiDtoMapper externalApiDtoMapper;

    @Override
    public List<GeneralCurrency> findByExternalApiName(ExternalApiName externalApiName) {
        var param = ExternalApiUrl.builder().externalApiName(externalApiName).build();
        var externalApiRateUrl = urlService.generateCurrencyUrlByExternalApiName(param);
        var responseBody = externalApiCaller.call(externalApiRateUrl);
        Class<?> dtoClass = externalApiName.getExternalApiProperty().getProperty().getCurrencyDtoClass();
        Object externalApiDto = externalApiDtoMapper.responseBodyToExternalApiDto(responseBody, dtoClass);
        TreeSet<GeneralCurrency> sortedGeneralCurrencies = new TreeSet<>(
                currencyMapper.currencyDtoListToCurrencyList(externalApiDto).getRates());
        return new ArrayList<>(sortedGeneralCurrencies);
    }


}
