package org.good.job.currency.project.controller;

import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.RateService;
import org.good.job.currency.project.service.impl.RateServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@WebMvcTest(RateController.class)
class RateControllerTest {

    @MockBean(value = RateServiceImpl.class)
    private RateService rateService;
    @InjectMocks
    private RateController rateController;


    @Test
    public void firstAndSecondParametersInGetExternalApiCurrencyRateByDateMethodAreCurrencyAndMoney() throws Exception {
        assertDoesNotThrow(() -> {
            rateController.getClass()
                    .getDeclaredMethod("getExternalApiCurrencyRateByDate", ExternalApiName.class, Currency.class,
                                       LocalDate.class);
        });
    }

}