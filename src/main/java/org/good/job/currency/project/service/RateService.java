package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.RateStatisticData;
import org.good.job.currency.project.entity.enums.ExternalApiName;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;


public interface RateService {

    GeneralRate getRateByExternalApiNameAndCurrencyAndDate(ExternalApiName externalApiName, Currency currencyCode,
                                                           LocalDate date);

    List<GeneralRate> getRateByExternalApiNameAndCurrencyAndDateRange(ExternalApiName externalApiName,
                                                                      String currency, LocalDate startDate,
                                                                      LocalDate endDate);

    RateStatisticData getStatistics(ExternalApiName externalApiName, String currency, LocalDate startDate,
                                    LocalDate endDate);

}
