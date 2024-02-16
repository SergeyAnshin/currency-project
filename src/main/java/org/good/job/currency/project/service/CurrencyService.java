package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.GeneralCurrency;
import org.good.job.currency.project.entity.enums.ExternalApiName;

import java.util.List;


public interface CurrencyService {

    List<GeneralCurrency> getAvailableCurrenciesByExternalApiName(ExternalApiName externalApiName);

    boolean isCurrencySupportedByExternalApi(ExternalApiName externalApiName, String currencyCode);

}
