package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dao.CurrencyDao;
import org.good.job.currency.project.entity.GeneralCurrency;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyDao currencyDao;


    //TODO Вернуть dto
    @Override
    public List<GeneralCurrency> getAvailableCurrenciesByExternalApiName(ExternalApiName externalApiName) {
        return currencyDao.findByExternalApiName(externalApiName);
    }

    @Override
    public boolean isCurrencySupportedByExternalApi(ExternalApiName externalApiName, String currencyCode) {
        return getAvailableCurrenciesByExternalApiName(externalApiName).stream()
                .anyMatch(currency -> Objects.equals(currency.getSellCurrencyCode(), currencyCode));
    }


    // TODO remove ?
    private List<Currency> convertGeneralCurrencySetToSellCurrencyList(List<GeneralCurrency> sortedGeneralCurrencies) {
        List<Currency> currencies = new ArrayList<>();
        for (GeneralCurrency generalCurrency : sortedGeneralCurrencies) {
            currencies.add(Currency.getInstance(generalCurrency.getSellCurrencyCode()));
        }
        return currencies;
    }

}
