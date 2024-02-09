package org.good.job.currency.project.controller;

import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.RateService;
import org.good.job.currency.project.service.exception.CurrencyNotSupportedByExternalApiException;
import org.good.job.currency.project.service.exception.RateNotFoundException;
import org.good.job.currency.project.service.impl.RateServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RateController.class)
class RateControllerTest {

    public static final String RATE_CONTROLLER_BASE_URL = "/rate";
    @MockBean(value = RateServiceImpl.class)
    private RateService rateService;
    @InjectMocks
    private RateController rateController;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void firstAndSecondParametersInGetExternalApiCurrencyRateByDateMethodAreCurrencyAndMoney() {
        assertDoesNotThrow(() -> {
            rateController.getClass()
                    .getDeclaredMethod("getExternalApiCurrencyRateByDate", ExternalApiName.class, Currency.class,
                                       LocalDate.class);
        });
    }

    @Test
    public void returnNotFoundIfExternalApiNotSupported() throws Exception {
        String externalApiName = "FAKE API";
        String currency = "USD";
        LocalDate currentDate = LocalDate.now();

        mockMvc.perform(get(RATE_CONTROLLER_BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("externalApiName", externalApiName)
                                .param("currency", currency)
                                .param("date", currentDate.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void returnNotFoundIfCurrencyNotSupportedByExternalApi() throws Exception {
        String externalApiName = ExternalApiName.ALFA_BANK.toString();
        Currency currency = Currency.getInstance("JPY");
        LocalDate currentDate = LocalDate.now();

        when(rateService.getRateByExternalApiNameAndCurrencyAndDate(ExternalApiName.ALFA_BANK, currency, currentDate))
                .thenThrow(CurrencyNotSupportedByExternalApiException.class);

        mockMvc.perform(get(RATE_CONTROLLER_BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("externalApiName", externalApiName)
                                .param("currency", currency.getCurrencyCode())
                                .param("date", currentDate.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void returnNotFoundIfRateNotFound() throws Exception {
        String externalApiName = ExternalApiName.ALFA_BANK.toString();
        Currency currency = Currency.getInstance("USD");
        LocalDate dateBeyondServiceExistence = LocalDate.of(2023, 2, 9);

        when(rateService.getRateByExternalApiNameAndCurrencyAndDate(ExternalApiName.ALFA_BANK, currency, dateBeyondServiceExistence))
                .thenThrow(RateNotFoundException.class);

        mockMvc.perform(get(RATE_CONTROLLER_BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("externalApiName", externalApiName)
                                .param("currency", currency.getCurrencyCode())
                                .param("date", dateBeyondServiceExistence.toString()))
                .andExpect(status().isNotFound());
    }
}