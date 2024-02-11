package org.good.job.currency.project.service.mapper;

import org.good.job.currency.project.dto.AlfaBankRate;
import org.good.job.currency.project.dto.BelarusBankRate;
import org.good.job.currency.project.dto.NbrbRateDto;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.entity.GeneralRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RateMapperTest {

    @Mock
    private BelarusBankRateMapperImpl belarusBankRateMapper;

    @InjectMocks
    private RateMapperImpl rateMapper;

    @Test
    void mapperCorrectlyMapsRatesForAlfaBank() {
        var externalApiUrl = ExternalApiUrl.builder().build();
        var sellRate = 3.38;
        var buyRate = 3.53;
        var alfaBankExternalApiDto = AlfaBankRate.builder()
                .sellRate(sellRate)
                .sellIso("EUR")
                .sellCode(978)
                .buyRate(buyRate)
                .buyIso("BYN")
                .buyCode(933)
                .quantity(1)
                .name("евро")
                .date(LocalDate.of(2024, 2, 9))
                .build();
        var alfaBankGeneralRateResult = GeneralRate.builder().buyRate(buyRate).sellRate(sellRate).build();
        GeneralRate generalRate = rateMapper.externalApiRateDtoToRate(alfaBankExternalApiDto, externalApiUrl);
        assertEquals(alfaBankGeneralRateResult, generalRate);
    }

    @Test
    void mapperCorrectlyMapsRatesForNbrb() {
        var externalApiUrl = ExternalApiUrl.builder().build();
        var sellRate = 3.2253;
        var buyRate = 0;
        var nbrbExternalApiDto = NbrbRateDto.builder()
                .currencyId(431)
                .date(LocalDateTime.of(2024, 2, 9, 0, 0))
                .currencyAbbreviation("USD")
                .foreignCurrencyUnitsNumber(1)
                .currencyName("Доллар США")
                .sellRate(sellRate)
                .build();
        var nbrbGeneralRateResult = GeneralRate.builder().buyRate(buyRate).sellRate(sellRate).build();

        var generalRate = rateMapper.externalApiRateDtoToRate(nbrbExternalApiDto, externalApiUrl);
        assertEquals(nbrbGeneralRateResult, generalRate);
    }

    @Test
    void mapperCorrectlyMapsRatesForBelarusBank() {
        var externalApiUrl = ExternalApiUrl.builder().currency(Currency.getInstance("USD")).build();
        var sellRate = 3.245;
        var buyRate = 3.185;
        var belarusBankRateExternalApiDto = BelarusBankRate.builder()
                .date(LocalDateTime.of(2024, 2, 9, 16, 30))
                .buyUsd(buyRate).sellUsd(sellRate).buyEur(3.4).sellEur(3.49).buyRub(3.47).sellRub(3.54).buyCny(4.42)
                .sellCny(4.54).buyConversionUsdEur(0.92).sellConversionUsdEur(1.065).buyConversionUsdRub(90.30)
                .sellConversionUsdRub(0.0108).sellConversionRubEur(96.5000).buyConversionRubEur(0.0100)
                .buyConversionCnyUsd(0.1365).sellConversionCnyUsd(7.0155).buyConversionCnyEur(0.1270)
                .sellConversionCnyEur(7.4890).buyConversionCnyRub(12.4860).sellConversionCnyRub(0.0765).build();
        var belarusBankGeneralRateResult = GeneralRate.builder().buyRate(buyRate).sellRate(sellRate).build();

        when(belarusBankRateMapper.externalApiRateDtoToRate(belarusBankRateExternalApiDto, externalApiUrl))
                .thenReturn(belarusBankGeneralRateResult);

        var generalRate = rateMapper.externalApiRateDtoToRate(belarusBankRateExternalApiDto, externalApiUrl);
        assertEquals(belarusBankGeneralRateResult, generalRate);
    }

}