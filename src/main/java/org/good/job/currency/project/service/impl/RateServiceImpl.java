package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dao.RateDao;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.CurrencyService;
import org.good.job.currency.project.service.RateService;
import org.good.job.currency.project.service.exception.CurrencyNotSupportedByExternalApiException;
import org.good.job.currency.project.service.exception.RateNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Currency;


@RequiredArgsConstructor

@Service
public class RateServiceImpl implements RateService {

    private final RateDao rateDao;
    private final CurrencyService currencyService;

    @Override
    public GeneralRate getRateByExternalApiNameAndCurrencyAndDate(ExternalApiName externalApiName,
                                                                  Currency currency,
                                                                  LocalDate date) {
        if (currencyService.isCurrencySupportedByExternalApi(externalApiName, currency)) {
            return rateDao.findByExternalApiNameAndCurrencyCodeAndDate(externalApiName, currency, date)
                    .orElseThrow(RateNotFoundException::new);
        } else {
            throw new CurrencyNotSupportedByExternalApiException();
        }
    }

}
