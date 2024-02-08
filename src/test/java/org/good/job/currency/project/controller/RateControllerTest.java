package org.good.job.currency.project.controller;

import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.RateService;
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
        mockMvc.perform(get(RATE_CONTROLLER_BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("externalApiName", "FFF_BANK")
                                .param("currency", "USD")
                                .param("date", "2024-02-08"))
                .andExpect(status().isNotFound());
    }

}