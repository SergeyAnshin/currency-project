package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.entity.ExternalApiUrl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Locale;

import static org.good.job.currency.project.entity.enums.ExternalApiName.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class ResourceBundleUrlServiceTest {

    @Mock
    private MessageSource messageSourceMock;

    @Autowired
    private MessageSource messageSource;
    @InjectMocks
    private ResourceBundleUrlService urlService;

    @Test
    void resourceBundleContainAlfaBankRateUrl() {
        var alfaBankRateUrl = "https://developerhub.alfabank.by:8273/partner/1.0.1/public/rates";
        var alfaBankRateUrlInBundle = messageSource.getMessage(ALFA_BANK.getExternalApiRateProperty()
                                                                       .getRateProperty()
                                                                       .getRateByCurrencyCodeAndDateCode(), null,
                                                               Locale.US);
        assertEquals(alfaBankRateUrl, alfaBankRateUrlInBundle);
    }

    @Test
    void resourceBundleContainNationalRateUrl() {
        var nationalBankRateUrl = "https://api.nbrb.by/exrates/rates/{0}?ondate={1}&parammode=2";
        var nationalBankRateUrlInBundle = messageSource.getMessage(NATIONAL_BANK.getExternalApiRateProperty()
                                                                           .getRateProperty()
                                                                           .getRateByCurrencyCodeAndDateCode(), null,
                                                                   Locale.US);
        assertEquals(nationalBankRateUrl, nationalBankRateUrlInBundle);
    }

    @Test
    void resourceBundleContainBelarusBankRateUrl() {
        var belarusBankRateUrl = "https://belarusbank.by/api/kurs_cards?kursDate";
        var belarusBankRateUrlInBundle = messageSource.getMessage(BELARUS_BANK.getExternalApiRateProperty()
                                                                          .getRateProperty()
                                                                          .getRateByCurrencyCodeAndDateCode(), null,
                                                                  Locale.US);
        assertEquals(belarusBankRateUrl, belarusBankRateUrlInBundle);
    }

    @Test
    void doesNotChangeParametersReturnedByMessageSource() {
        var urlParameters = ExternalApiUrl.builder()
                .externalApiName(NATIONAL_BANK)
                .currency(Currency.getInstance("USD"))
                .date(LocalDate.now())
                .build();
        var nationalBankRateUrl = "https://api.nbrb.by/exrates/rates/USD?ondate=" + LocalDate.now() + "&parammode=2";
        var rateMessageCode =
                NATIONAL_BANK.getExternalApiRateProperty().getRateProperty().getRateByCurrencyCodeAndDateCode();
        var currencyCode = urlParameters.getCurrency().getCurrencyCode();
        var date = urlParameters.getDate();
        when(messageSourceMock.getMessage(rateMessageCode, new Object[]{ currencyCode, date }, Locale.US)).thenReturn(
                nationalBankRateUrl);
        assertEquals(nationalBankRateUrl, urlService.generateRateUrlByExternalApiNameAndCurrencyAndDate(urlParameters));
    }

}