package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dao.CurrencyDao;
import org.good.job.currency.project.entity.GeneralCurrency;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyDao currencyDao;

    @Override
    public List<String> getAvailableCurrenciesByExternalApiName(ExternalApiName externalApiName) {
        return currencyDao.findByExternalApiName(externalApiName)
                .stream()
                .map(GeneralCurrency::getSellCurrencyCode)
                .toList();
    }

    @Override
    public boolean isCurrencySupportedByExternalApi(ExternalApiName externalApiName, String currencyCode) {
        return getAvailableCurrenciesByExternalApiName(externalApiName).stream()
                .anyMatch(currency -> Objects.equals(currency, currencyCode));
    }

}
