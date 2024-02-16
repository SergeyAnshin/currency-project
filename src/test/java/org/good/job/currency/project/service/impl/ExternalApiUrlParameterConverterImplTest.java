package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.entity.ExternalApiUrl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import static org.good.job.currency.project.entity.enums.ExternalApiName.ALFA_BANK;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ExternalApiUrlParameterConverterImplTest {

    @Autowired
    private ExternalApiUrlParameterConverterImpl converter;

    @Test
    void convert() {
        var param = ExternalApiUrl.builder()
                .externalApiName(ALFA_BANK)
                .currency("USD")
                .date(LocalDate.now())
                .build();

        Map<String, String> expectedMap = new HashMap<>() {{
            put("apiName", ALFA_BANK.name());
            put("currencyCode", param.getCurrency().toString());
            put("date", LocalDate.now().toString());
        }};
        assertEquals(expectedMap, converter.convert(param));
    }

}