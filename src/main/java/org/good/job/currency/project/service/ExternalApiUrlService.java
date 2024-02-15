package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.ExternalApiUrl;


//TODO Можно сделать один общий метод
public interface ExternalApiUrlService {
    String generateCurrencyUrlByExternalApiName(ExternalApiUrl param);

    String generateRateUrlByExternalApiNameAndCurrencyAndDate(ExternalApiUrl param);


}
