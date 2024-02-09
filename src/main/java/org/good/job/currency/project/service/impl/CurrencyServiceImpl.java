package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;


@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Override
    public List<Currency> getAvailableCurrenciesByExternalApi(ExternalApiName externalApiName) {
//        externalApiName.getExternalApiRateProperty().
        return null;
    }

    @Override
    public boolean isCurrencySupportedByExternalApi(ExternalApiName externalApiName, Currency currency) {
        return false;
    }

}
