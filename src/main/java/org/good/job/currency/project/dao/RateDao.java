package org.good.job.currency.project.dao;

import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.entity.enums.ExternalApiName;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;


public interface RateDao {

    Optional<GeneralRate> findByExternalApiNameAndCurrencyCodeAndDate(UserRequestParametersData userRequestParameters);

}
