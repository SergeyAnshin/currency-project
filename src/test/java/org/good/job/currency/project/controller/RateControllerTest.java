package org.good.job.currency.project.controller;

import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.RateService;
import org.good.job.currency.project.service.exception.CurrencyNotSupportedByExternalApiException;
import org.good.job.currency.project.service.exception.RateNotFoundException;
import org.good.job.currency.project.service.impl.RateServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RateController.class)
class RateControllerTest {

    public static final String RATE_CONTROLLER_BASE_URL = "/rate";
    @MockBean(value = RateServiceImpl.class)
    private RateService rateService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void returnNotFoundIfExternalApiNotSupported() throws Exception {
        var externalApiName = "FAKE API";
        var currency = "USD";
        var currentDate = LocalDate.now();

        mockMvc.perform(get(RATE_CONTROLLER_BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("externalApiName", externalApiName)
                                .param("currency", currency)
                                .param("date", currentDate.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void returnNotFoundIfCurrencyNotSupportedByExternalApi() throws Exception {
        var externalApiName = ExternalApiName.ALFA_BANK;
        var currencyCode = "JPY";
        var currentDate = LocalDate.now();
        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(currencyCode)
                .localCurrencyCode("BYN")
                .date(currentDate)
                .build();
        when(rateService.getRateByExternalApiNameAndCurrencyAndDate(userRequestParameters))
                .thenThrow(CurrencyNotSupportedByExternalApiException.class);

        mockMvc.perform(get(RATE_CONTROLLER_BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("externalApiName", externalApiName.name())
                                .param("currency", currencyCode)
                                .param("date", currentDate.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void returnNotFoundIfRateNotFound() throws Exception {
        var externalApiName = ExternalApiName.ALFA_BANK;
        var currencyCode = "USD";
        var dateBeyondServiceExistence = LocalDate.of(2023, 2, 9);

        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(currencyCode)
                .localCurrencyCode("BYN")
                .date(dateBeyondServiceExistence)
                .build();

        when(rateService.getRateByExternalApiNameAndCurrencyAndDate(userRequestParameters))
                .thenThrow(RateNotFoundException.class);

        mockMvc.perform(get(RATE_CONTROLLER_BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("externalApiName", externalApiName.name())
                                .param("currency", currencyCode)
                                .param("date", dateBeyondServiceExistence.toString()))
                .andExpect(status().isNotFound());
    }

}