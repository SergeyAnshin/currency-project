package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dao.CurrencyDao;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.entity.GeneralCurrency;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.CurrencyService;
import org.good.job.currency.project.service.ExternalApiCaller;
import org.good.job.currency.project.service.ExternalApiDtoMapper;
import org.good.job.currency.project.service.ExternalApiUrlService;
import org.good.job.currency.project.service.mapper.CurrencyMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.TreeSet;


@RequiredArgsConstructor

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyDao currencyDao;


    @Override
    public List<Currency> getAvailableCurrenciesByExternalApiName(ExternalApiName externalApiName) {
        return convertGeneralCurrencySetToSellCurrencyList(currencyDao.findByExternalApiName(externalApiName));
    }

    @Override
    public boolean isCurrencySupportedByExternalApi(ExternalApiName externalApiName, Currency currency) {
        return getAvailableCurrenciesByExternalApiName(externalApiName).contains(currency);
    }


    private List<Currency> convertGeneralCurrencySetToSellCurrencyList(List<GeneralCurrency> sortedGeneralCurrencies) {
        List<Currency> currencies = new ArrayList<>();
        for (GeneralCurrency generalCurrency : sortedGeneralCurrencies) {
            currencies.add(Currency.getInstance(generalCurrency.getSellCurrencyCode()));
        }
        return currencies;
    }

}
