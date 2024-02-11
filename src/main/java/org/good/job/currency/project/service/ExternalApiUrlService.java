package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.ExternalApiUrlOld;


//TODO Можно сделать один общий метод
public interface ExternalApiUrlService {

    String generateRateUrlByExternalApiNameAndCurrencyAndDate(ExternalApiUrlOld param);

}
