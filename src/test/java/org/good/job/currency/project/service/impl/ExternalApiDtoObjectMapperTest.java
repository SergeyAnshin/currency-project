package org.good.job.currency.project.service.impl;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.good.job.currency.project.dto.AlfaBankDto;
import org.good.job.currency.project.dto.AlfaBankDtoList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ExternalApiDtoObjectMapperTest {

    @Autowired
    private ExternalApiDtoObjectMapper externalApiDtoMapper;

    @Test
    void responseBodyToExternalApiDtoCorrectlyMapsAlfaBankDto() {
        var alfaBankResponseBody = """
                {
                    "rates": [
                        {
                            "sellRate": 94.000000,
                            "sellIso": "EUR",
                            "sellCode": 978,
                            "buyRate": 104.000000,
                            "buyIso": "RUB",
                            "buyCode": 643,
                            "quantity": 1,
                            "name": null,
                            "date": "16.02.2024"
                        },
                        {
                            "sellRate": 87.000000,
                            "sellIso": "USD",
                            "sellCode": 840,
                            "buyRate": 97.000000,
                            "buyIso": "RUB",
                            "buyCode": 643,
                            "quantity": 1,
                            "name": null,
                            "date": "16.02.2024"
                        }
                    ]
                }
                """;
        var alfaBankDto = AlfaBankDto.builder()
                .sellRate(94.000000)
                .sellCurrencyCode("EUR")
                .sellCode(978)
                .buyRate(104.000000)
                .buyCurrencyCode("RUB")
                .buyCode(643)
                .quantity(1)
                .name(null)
                .date(LocalDate.of(2024, 2, 16))
                .build();
        var alfaBankDto1 = AlfaBankDto.builder()
                .sellRate(87.000000)
                .sellCurrencyCode("USD")
                .sellCode(840)
                .buyRate(97.000000)
                .buyCurrencyCode("RUB")
                .buyCode(643)
                .quantity(1)
                .name(null)
                .date(LocalDate.of(2024, 2, 16))
                .build();
        var alfaBankDtos = List.of(alfaBankDto, alfaBankDto1);
        var alfaBankDtoList = AlfaBankDtoList.builder().dtoList(alfaBankDtos).build();

        assertEquals(alfaBankDtoList,
                     externalApiDtoMapper.responseBodyToExternalApiDto(alfaBankResponseBody, AlfaBankDtoList.class));
    }

}