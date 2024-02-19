package org.good.job.currency.project.service.mapper;

import org.good.job.currency.project.dto.AlfaBankDto;
import org.good.job.currency.project.dto.BelarusBankConvertedDto;
import org.good.job.currency.project.dto.NationalBankDto;
import org.good.job.currency.project.dto.enums.ConstCurrency;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class RateMapperTest {

    @InjectMocks
    private RateMapperImpl rateMapper;

    @Test
    void mapperCorrectlyMapsRatesForAlfaBank() {
        var externalApiUrl = UserRequestParametersData.builder().build();
        var sellRate = 3.38;
        var buyRate = 3.53;
        var date = LocalDate.of(2024, 2, 9);
        var alfaBankExternalApiDto = AlfaBankDto.builder()
                .sellRate(sellRate)
                .sellCurrencyCode("EUR")
                .sellCode(978)
                .buyRate(buyRate)
                .buyCurrencyCode("BYN")
                .buyCode(933)
                .quantity(1)
                .name("евро")
                .date(date)
                .build();
        var alfaBankGeneralRateResult = GeneralRate.builder().buyRate(buyRate).sellRate(sellRate).date(date).build();
        GeneralRate generalRate = rateMapper.externalApiRateDtoToRate(alfaBankExternalApiDto, externalApiUrl);
        assertEquals(alfaBankGeneralRateResult, generalRate);
    }

    @Test
    void mapperCorrectlyMapsRatesForNbrb() {
        var externalApiUrl = UserRequestParametersData.builder().build();
        var sellRate = 3.2253;
        var buyRate = 0;
        var date = LocalDateTime.of(2024, 2, 9, 0, 0);
        var nbrbExternalApiDto = NationalBankDto.builder()
                .currencyId(431)
                .date(date)
                .sellCurrencyCode("USD")
                .foreignCurrencyUnitsNumber(1)
                .currencyName("Доллар США")
                .sellRate(sellRate)
                .build();
        var nbrbGeneralRateResult =
                GeneralRate.builder().buyRate(buyRate).sellRate(sellRate).date(date.toLocalDate()).build();

        var generalRate = rateMapper.externalApiRateDtoToRate(nbrbExternalApiDto, externalApiUrl);
        assertEquals(nbrbGeneralRateResult, generalRate);
    }

    @Test
    void mapperCorrectlyMapsRatesForBelarusBank() {
        var externalApiUrl = UserRequestParametersData.builder().targetCurrencyCode("USD").build();
        var sellRate = 3.245;
        var buyRate = 3.185;
        var dateTime = LocalDateTime.of(2024, 2, 9, 16, 30);
        var belarusBankRateExternalApiDto = BelarusBankConvertedDto.builder()
                .date(dateTime)
                .sellCurrencyCode(ConstCurrency.USD.name())
                .sellRate(sellRate)
                .buyRate(buyRate).build();
        var belarusBankGeneralRateResult =
                GeneralRate.builder().buyRate(buyRate).sellRate(sellRate).date(dateTime.toLocalDate()).build();

        var generalRate = rateMapper.externalApiRateDtoToRate(belarusBankRateExternalApiDto, externalApiUrl);
        assertEquals(belarusBankGeneralRateResult, generalRate);
    }

}