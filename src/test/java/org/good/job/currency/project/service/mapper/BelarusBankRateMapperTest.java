package org.good.job.currency.project.service.mapper;

import org.good.job.currency.project.dto.BelarusBankRate;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.entity.GeneralRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class BelarusBankRateMapperTest {

    @Autowired
    private BelarusBankRateMapperImpl belarusBankRateMapper;

    @Test
    void mapperExtractsRatesFromCorrectFields() {
        var externalApiUrlWithUsd = ExternalApiUrl.builder().currency(Currency.getInstance("USD")).build();
        var externalApiUrlWithEur = ExternalApiUrl.builder().currency(Currency.getInstance("EUR")).build();
        var externalApiUrlWithRub = ExternalApiUrl.builder().currency(Currency.getInstance("RUB")).build();
        var externalApiUrlWithCny = ExternalApiUrl.builder().currency(Currency.getInstance("CNY")).build();
        var sellRateUsd = 3.245;
        var buyRateUsd = 3.185;
        var sellRateEur = 3.49;
        var buyRateEur = 3.4;
        var sellRateRub = 3.54;
        var buyRateRub = 3.47;
        var sellRateCny = 4.54;
        var buyRateCny = 4.42;
        var belarusBankRateExternalApiDto = BelarusBankRate.builder()
                .date(LocalDateTime.of(2024, 2, 9, 16, 30)).buyUsd(buyRateUsd)
                .sellUsd(sellRateUsd).buyEur(buyRateEur).sellEur(sellRateEur).buyRub(buyRateRub).sellRub(sellRateRub)
                .buyCny(buyRateCny).sellCny(sellRateCny).buyConversionUsdEur(0.92).sellConversionUsdEur(1.065)
                .buyConversionUsdRub(90.30).sellConversionUsdRub(0.0108).sellConversionRubEur(96.5000)
                .buyConversionRubEur(0.0100).buyConversionCnyUsd(0.1365).sellConversionCnyUsd(7.0155)
                .buyConversionCnyEur(0.1270).sellConversionCnyEur(7.4890).buyConversionCnyRub(12.4860)
                .sellConversionCnyRub(0.0765).build();
        var belarusBankWithUsdResult = GeneralRate.builder().buyRate(buyRateUsd).sellRate(sellRateUsd).build();
        var belarusBankWithEurResult = GeneralRate.builder().buyRate(buyRateEur).sellRate(sellRateEur).build();
        var belarusBankWithRubResult = GeneralRate.builder().buyRate(buyRateRub).sellRate(sellRateRub).build();
        var belarusBankWithCnyResult = GeneralRate.builder().buyRate(buyRateCny).sellRate(sellRateCny).build();


        assertEquals(belarusBankWithUsdResult,
                     belarusBankRateMapper.externalApiRateDtoToRate(belarusBankRateExternalApiDto,
                                                                    externalApiUrlWithUsd));
        assertEquals(belarusBankWithEurResult,
                     belarusBankRateMapper.externalApiRateDtoToRate(belarusBankRateExternalApiDto,
                                                                    externalApiUrlWithEur));
        assertEquals(belarusBankWithRubResult,
                     belarusBankRateMapper.externalApiRateDtoToRate(belarusBankRateExternalApiDto,
                                                                    externalApiUrlWithRub));
        assertEquals(belarusBankWithCnyResult,
                     belarusBankRateMapper.externalApiRateDtoToRate(belarusBankRateExternalApiDto,
                                                                    externalApiUrlWithCny));
    }

}