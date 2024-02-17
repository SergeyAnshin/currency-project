package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.RateStatisticData;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.entity.enums.ExternalApiName;

import java.time.LocalDate;
import java.util.List;


public interface RateService {

    GeneralRate getRateByExternalApiNameAndCurrencyAndDate(UserRequestParametersData userRequestParameters);

    List<GeneralRate> getRateByExternalApiNameAndCurrencyAndDateRange(ExternalApiName externalApiName,
                                                                      String currencyCode, LocalDate startDate,
                                                                      LocalDate endDate);

    RateStatisticData getStatistics(ExternalApiName externalApiName, String currencyCode, LocalDate startDate,
                                    LocalDate endDate);

}
