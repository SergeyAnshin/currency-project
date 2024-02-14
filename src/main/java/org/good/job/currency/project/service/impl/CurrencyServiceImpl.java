package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.ConfigProperties;
import org.good.job.currency.project.dao.ParameterConverter;
import org.good.job.currency.project.entity.CurrencyList;
import org.good.job.currency.project.entity.GeneralCurrency;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.CurrencyService;
import org.good.job.currency.project.service.ExternalApiCaller;
import org.good.job.currency.project.service.ExternalApiDtoMapper;
import org.good.job.currency.project.service.mapper.CurrencyMapper;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;


@RequiredArgsConstructor

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final ConfigProperties configProperties;
    private final ParameterConverter parameterConverter;
    private final ExternalApiCaller externalApiCaller;
    private final ExternalApiDtoMapper externalApiDtoMapper;
    private final CurrencyMapper currencyMapper;

    @Override
    public List<Currency> getAvailableCurrenciesByBankName(String bankName) {
//        ExternalApiParameters parameters = ExternalApiParameters.builder().externalApiName(bankName).build();
//        HashMap<String, Bank> banksMap = configProperties.getBanks();
//        Bank bank = banksMap.get(bankName);
//        if (bank == null) {
//            throw new RuntimeException("Bank '" + bankName + "' is not exists");
//        }
//        ExternalApiUrl currencyExternalApiUrl = bank.getCurrencyExternalApiUrl();
//        HashMap<String, String> parametersHashMap = parameters.getParametersHashMap();
//        String currencyUrlWithParameters = parameterConverter.convert(currencyExternalApiUrl, parametersHashMap);
//        String responseBody = externalApiCaller.call(currencyUrlWithParameters);
//        String responseBody = externalApiCaller.call("https://api.nbrb.by/exrates/rates?periodicity=0");
//        String responseBody = externalApiCaller.call("https://developerhub.alfabank.by:8273/partner/1.0.1/public/rates");
        String responseBody = externalApiCaller.call("https://belarusbank.by/api/kurs_cards?kursDate");
        Class<?> dtoClass = ExternalApiName.valueOf(bankName).getExternalApiProperty().getProperty().getCurrencyDtoClass();
        // TODO реализовать dto для всех вариантов api
        Object externalApiDto = externalApiDtoMapper.responseBodyToExternalApiDto(responseBody, dtoClass);
        CurrencyList currencies = currencyMapper.currencyDtoListToCurrencyList(externalApiDto);

        return null;
    }

    @Override
    public boolean isCurrencySupportedByExternalApi(ExternalApiName externalApiName, Currency currency) {
        return true;
    }

}
