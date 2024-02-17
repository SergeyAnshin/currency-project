package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dao.RateDao;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.RateStatisticData;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.CurrencyService;
import org.good.job.currency.project.service.RateService;
import org.good.job.currency.project.service.StatisticService;
import org.good.job.currency.project.service.exception.CurrencyNotSupportedByExternalApiException;
import org.good.job.currency.project.service.exception.RateNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor

@Service
public class RateServiceImpl implements RateService {

    private final RateDao rateDao;
    private final CurrencyService currencyService;
    private final StatisticService statisticService;

    @Override
    public GeneralRate getRateByExternalApiNameAndCurrencyAndDate(UserRequestParametersData userRequestParameters) {
        var externalApiName = userRequestParameters.getExternalApiName();
        var targetCurrencyCode = userRequestParameters.getTargetCurrencyCode();
        if (currencyService.isCurrencySupportedByExternalApi(externalApiName, targetCurrencyCode)) {
            return rateDao.findByExternalApiNameAndCurrencyCodeAndDate(userRequestParameters)
                    .orElseThrow(RateNotFoundException::new);
        } else {
            throw new CurrencyNotSupportedByExternalApiException();
        }
    }

    @Override
    public RateStatisticData getStatistics(ExternalApiName externalApiName, String currencyCode, LocalDate startDate,
                                           LocalDate endDate) {
        var generalRates =
                getRateByExternalApiNameAndCurrencyAndDateRange(externalApiName, currencyCode, startDate, endDate);
        return statisticService.getStatistics(generalRates);

    }

    @Override
    public List<GeneralRate> getRateByExternalApiNameAndCurrencyAndDateRange(ExternalApiName externalApiName,
                                                                             String currencyCode, LocalDate startDate,
                                                                             LocalDate endDate) {
        return null;
    }

}
