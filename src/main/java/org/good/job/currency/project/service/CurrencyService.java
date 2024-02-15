package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.enums.ExternalApiName;

import java.util.Currency;
import java.util.List;


public interface CurrencyService {
    List<Currency> getAvailableCurrenciesByExternalApiName(ExternalApiName externalApiName);

    boolean isCurrencySupportedByExternalApi(ExternalApiName externalApiName, Currency currency);
}
