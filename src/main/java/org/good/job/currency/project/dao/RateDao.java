package org.good.job.currency.project.dao;

import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.UserRequestParametersData;

import java.util.List;
import java.util.Optional;


public interface RateDao {

    Optional<GeneralRate> findByExternalApiNameAndCurrencyCodeAndDate(UserRequestParametersData userRequestParameters);

    List<GeneralRate> findByExternalApiNameAndCurrencyCodeAndPeriod(UserRequestParametersData userRequestParameters);

}
