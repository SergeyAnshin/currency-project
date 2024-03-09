package org.good.job.currency.project.service;

import org.good.job.currency.project.dto.UserRequestParameters;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.RateStatisticData;
import org.good.job.currency.project.entity.UserRequestParametersData;

import java.util.List;


public interface RateService {

    GeneralRate getRateByExternalApiNameAndCurrencyAndDate(UserRequestParametersData userRequestParameters);

    List<GeneralRate> getCurrencyRatesByPeriod(UserRequestParametersData userRequestParameters);

    RateStatisticData getStatistics(UserRequestParameters userRequestParameters);

}
