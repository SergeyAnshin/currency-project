package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.dao.RateDao;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.CurrencyService;
import org.good.job.currency.project.service.exception.CurrencyNotSupportedByExternalApiException;
import org.good.job.currency.project.service.exception.RateNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

import static org.good.job.currency.project.entity.enums.ExternalApiName.ALFA_BANK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RateServiceImplTest {

    @Mock
    private RateDao rateDao;
    @Mock
    private CurrencyService currencyService;
    @InjectMocks
    private RateServiceImpl rateService;

    @Test
    void getRateByExternalApiNameAndCurrencyAndDateMethodNotChangeExistingRate() {
        var externalApiName = ALFA_BANK;
        var currencyCode = "JPY";
        var currentDate = LocalDate.now();
        var existingRate = GeneralRate.builder().buyRate(1.1).sellRate(2.1).build();
        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(currencyCode)
                .localCurrencyCode("BYN")
                .date(currentDate)
                .build();

        when(currencyService.isCurrencySupportedByExternalApi(externalApiName, currencyCode)).thenReturn(true);
        when(rateDao.findByExternalApiNameAndCurrencyCodeAndDate(userRequestParameters))
                .thenReturn(Optional.of(existingRate));

        var rate = rateService.getRateByExternalApiNameAndCurrencyAndDate(userRequestParameters);

        assertAll(() -> {
            assertEquals(existingRate.getBuyRate(), rate.getBuyRate());
            assertEquals(existingRate.getSellRate(), rate.getSellRate());
        });
    }

    @Test
    void getRateByExternalApiNameAndCurrencyAndDateMethodThrowExceptionIfCurrencyNotSupportedExternalApi() {
        var externalApiName = ALFA_BANK;
        var currencyCode = "JPY";
        var currentDate = LocalDate.now();
        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(currencyCode)
                .localCurrencyCode("BYN")
                .date(currentDate)
                .build();
        when(currencyService.isCurrencySupportedByExternalApi(externalApiName, currencyCode)).thenReturn(false);

        assertThrows(CurrencyNotSupportedByExternalApiException.class,
                     () -> rateService.getRateByExternalApiNameAndCurrencyAndDate(userRequestParameters));
    }

    @Test
    void getRateByExternalApiNameAndCurrencyAndDateMethodThrowExceptionIfRateNotFound() {
        var externalApiName = ALFA_BANK;
        var currencyCode = "JPY";
        var currentDate = LocalDate.now();
        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(currencyCode)
                .localCurrencyCode("BYN")
                .date(currentDate)
                .build();

        when(currencyService.isCurrencySupportedByExternalApi(externalApiName, currencyCode)).thenReturn(true);
        when(rateDao.findByExternalApiNameAndCurrencyCodeAndDate(userRequestParameters))
                .thenReturn(Optional.empty());

        assertThrows(RateNotFoundException.class,
                     () -> rateService.getRateByExternalApiNameAndCurrencyAndDate(userRequestParameters));
    }

}