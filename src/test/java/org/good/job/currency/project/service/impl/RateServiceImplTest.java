package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.dao.RateDao;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.CurrencyService;
import org.good.job.currency.project.service.exception.CurrencyNotSupportedByExternalApiException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

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
        var currency = Currency.getInstance("JPY");
        var currentDate = LocalDate.now();
        var existingRate = GeneralRate.builder().buyRate(1.1).sellRate(2.1).build();

        when(currencyService.isCurrencySupportedByExternalApi(ExternalApiName.ALFA_BANK, currency)).thenReturn(true);
        when(rateDao.findByExternalApiNameAndCurrencyCodeAndDate(ExternalApiName.ALFA_BANK, currency, currentDate))
                .thenReturn(Optional.of(existingRate));

        GeneralRate rate = rateService.getRateByExternalApiNameAndCurrencyAndDate(ExternalApiName.ALFA_BANK, currency,
                                                                                  currentDate);

        assertAll(() -> {
            assertEquals(existingRate.getBuyRate(), rate.getBuyRate());
            assertEquals(existingRate.getSellRate(), rate.getSellRate());
        });
    }

    @Test
    void getRateByExternalApiNameAndCurrencyAndDateMethodThrowExceptionIfCurrencyNotSupportedExternalApi() {
        var currency = Currency.getInstance("JPY");
        var currentDate = LocalDate.now();

        when(currencyService.isCurrencySupportedByExternalApi(ExternalApiName.ALFA_BANK, currency)).thenReturn(false);

        assertThrows(CurrencyNotSupportedByExternalApiException.class,
                     () -> rateService.getRateByExternalApiNameAndCurrencyAndDate(ExternalApiName.ALFA_BANK, currency,
                                                                                  currentDate));
    }

}