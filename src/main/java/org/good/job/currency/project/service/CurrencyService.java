package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.enums.ExternalApiName;

import java.util.Currency;


public interface CurrencyService {

    boolean isCurrencySupportedByExternalApi(ExternalApiName externalApiName, Currency currency);

}
