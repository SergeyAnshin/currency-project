package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.UserRequestParametersData;


//TODO Можно сделать один общий метод
public interface ExternalApiUrlService {

    String generateCurrencyUrlByExternalApiName(UserRequestParametersData userRequestParameters);

    String generateRateUrlByExternalApiNameAndCurrencyAndDate(UserRequestParametersData userRequestParameters);


}
