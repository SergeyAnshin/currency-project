package org.good.job.currency.project.dto.extractor.impl;

import org.good.job.currency.project.dto.BelarusBankConvertedDto;
import org.good.job.currency.project.dto.BelarusBankDtoList;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.service.exception.RateNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.List;

import static org.good.job.currency.project.entity.enums.ExternalApiName.BELARUS_BANK;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RequiredExternalApiRateDtoExtractorTest {

    @Autowired
    private RequiredExternalApiRateDtoExtractor extractor;
    @Autowired
    private CurrencyAndLocalCurrencyAndPeriodStrategy currencyAndPeriodStrategy;
    @Autowired
    private CurrencyAndLocalCurrencyAndDateStrategy currencyAndDateStrategy;


    private final BelarusBankConvertedDto belarusBankDto1 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 20, 17, 0))
            .sellCurrencyCode("USD")
            .sellRate(3.27)
            .buyRate(3.21)
            .build();
    private final BelarusBankConvertedDto belarusBankDto2 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 20, 17, 0))
            .sellCurrencyCode("EUR")
            .sellRate(3.52)
            .buyRate(3.43)
            .build();
    private final BelarusBankConvertedDto belarusBankDto3 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 20, 17, 0))
            .sellCurrencyCode("RUB")
            .sellRate(3.51)
            .buyRate(3.44)
            .build();
    private final BelarusBankConvertedDto belarusBankDto4 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 20, 17, 0))
            .sellCurrencyCode("CNY")
            .sellRate(4.58)
            .buyRate(4.48)
            .build();
    private final BelarusBankConvertedDto belarusBankDto5 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 19, 17, 0))
            .sellCurrencyCode("USD")
            .sellRate(3.26)
            .buyRate(3.21)
            .build();
    private final BelarusBankConvertedDto belarusBankDto6 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 19, 17, 0))
            .sellCurrencyCode("EUR")
            .sellRate(3.505)
            .buyRate(3.43)
            .build();
    private final BelarusBankConvertedDto belarusBankDto7 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 19, 17, 0))
            .sellCurrencyCode("RUB")
            .sellRate(3.505)
            .buyRate(3.44)
            .build();
    private final BelarusBankConvertedDto belarusBankDto8 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 19, 17, 0))
            .sellCurrencyCode("CNY")
            .sellRate(4.57)
            .buyRate(4.47)
            .build();
    private final BelarusBankConvertedDto belarusBankDto9 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 18, 0, 0))
            .sellCurrencyCode("USD")
            .sellRate(3.275)
            .buyRate(3.21)
            .build();
    private final BelarusBankConvertedDto belarusBankDto10 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 18, 0, 0))
            .sellCurrencyCode("EUR")
            .sellRate(3.52)
            .buyRate(3.43)
            .build();
    private final BelarusBankConvertedDto belarusBankDto11 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 18, 0, 0))
            .sellCurrencyCode("RUB")
            .sellRate(3.52)
            .buyRate(3.45)
            .build();
    private final BelarusBankConvertedDto belarusBankDto12 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 18, 0, 0))
            .sellCurrencyCode("CNY")
            .sellRate(4.56)
            .buyRate(4.46)
            .build();
    private final BelarusBankConvertedDto belarusBankDto13 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 17, 0, 0))
            .sellCurrencyCode("USD")
            .sellRate(3.275)
            .buyRate(3.21)
            .build();
    private final BelarusBankConvertedDto belarusBankDto14 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 17, 0, 0))
            .sellCurrencyCode("EUR")
            .sellRate(3.52)
            .buyRate(3.43)
            .build();
    private final BelarusBankConvertedDto belarusBankDto15 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 17, 0, 0))
            .sellCurrencyCode("RUB")
            .sellRate(3.52)
            .buyRate(3.45)
            .build();
    private final BelarusBankConvertedDto belarusBankDto16 = BelarusBankConvertedDto.builder()
            .date(LocalDateTime.of(2024, 2, 17, 0, 0))
            .sellCurrencyCode("CNY")
            .sellRate(4.56)
            .buyRate(4.46)
            .build();
    private final List<BelarusBankConvertedDto> belarusBankDtos =
            List.of(belarusBankDto1, belarusBankDto2, belarusBankDto3, belarusBankDto4, belarusBankDto5,
                    belarusBankDto6, belarusBankDto7, belarusBankDto8, belarusBankDto9, belarusBankDto10,
                    belarusBankDto11, belarusBankDto12, belarusBankDto13, belarusBankDto14, belarusBankDto15,
                    belarusBankDto16);
    private final BelarusBankDtoList belarusBankDtoList = BelarusBankDtoList.builder().dtoList(belarusBankDtos).build();

    @Test
    void areCorrectlyExtractIfRateMatchesCurrencyAndLocalCurrencyAndPeriod() {
        var userRequestParameters1 = UserRequestParametersData.builder()
                .externalApiName(BELARUS_BANK)
                .targetCurrencyCode("USD")
                .localCurrencyCode("BYN")
                .periodStartDate(LocalDate.of(2024, 2, 18))
                .periodEndDate(LocalDate.of(2024, 2, 19))
                .build();

        var userRequestParameters2 = UserRequestParametersData.builder()
                .externalApiName(BELARUS_BANK)
                .targetCurrencyCode("EUR")
                .localCurrencyCode("BYN")
                .periodStartDate(LocalDate.of(2024, 2, 18))
                .periodEndDate(LocalDate.of(2024, 2, 21))
                .build();

        var expectedBelarusBankDtos1 = new ArrayDeque<>(List.of(belarusBankDto9, belarusBankDto5));
        var expectedBelarusBankDtos2 = new ArrayDeque<>(List.of(belarusBankDto10, belarusBankDto6, belarusBankDto2));

        assertAll(() -> {
            assertEquals(expectedBelarusBankDtos1.stream().toList(),
                         extractor.extract(belarusBankDtoList, userRequestParameters1, currencyAndPeriodStrategy)
                                 .stream()
                                 .toList());
            assertEquals(expectedBelarusBankDtos2.stream().toList(),
                         extractor.extract(belarusBankDtoList, userRequestParameters2, currencyAndPeriodStrategy)
                                 .stream()
                                 .toList());
        });
    }

    @Test
    public void areCorrectlyExtractIfRateMatchesCurrencyAndLocalCurrencyAndDate() {
        var userRequestParameters1 = UserRequestParametersData.builder()
                .externalApiName(BELARUS_BANK)
                .targetCurrencyCode("CNY")
                .localCurrencyCode("BYN")
                .date(LocalDate.of(2024, 2, 17))
                .periodStartDate(LocalDate.of(2024, 2, 18))
                .periodEndDate(LocalDate.of(2024, 2, 19))
                .build();

        var userRequestParameters2 = UserRequestParametersData.builder()
                .externalApiName(BELARUS_BANK)
                .targetCurrencyCode("RUB")
                .localCurrencyCode("BYN")
                .date(LocalDate.of(2024, 2, 18))
                .periodStartDate(LocalDate.of(2024, 2, 18))
                .periodEndDate(LocalDate.of(2024, 2, 21))
                .build();

        var expectedBelarusBankDtos1 = new ArrayDeque<>(List.of(belarusBankDto16));
        var expectedBelarusBankDtos2 = new ArrayDeque<>(List.of(belarusBankDto11));

        assertAll(() -> {
            assertEquals(expectedBelarusBankDtos1.stream().toList(),
                         extractor.extract(belarusBankDtoList, userRequestParameters1, currencyAndDateStrategy)
                                 .stream()
                                 .toList());
            assertEquals(expectedBelarusBankDtos2.stream().toList(),
                         extractor.extract(belarusBankDtoList, userRequestParameters2, currencyAndDateStrategy)
                                 .stream()
                                 .toList());
        });
    }

    @Test
    public void throwExceptionIfCurrencyNotMatch() {
        var userRequestParameters1 = UserRequestParametersData.builder()
                .externalApiName(BELARUS_BANK)
                .targetCurrencyCode("AUD")
                .localCurrencyCode("BYN")
                .date(LocalDate.of(2024, 2, 17))
                .periodStartDate(LocalDate.of(2024, 2, 18))
                .periodEndDate(LocalDate.of(2024, 2, 19))
                .build();

        var userRequestParameters2 = UserRequestParametersData.builder()
                .externalApiName(BELARUS_BANK)
                .targetCurrencyCode("CAD")
                .localCurrencyCode("BYN")
                .date(LocalDate.of(2024, 2, 18))
                .periodStartDate(LocalDate.of(2024, 2, 18))
                .periodEndDate(LocalDate.of(2024, 2, 21))
                .build();

        var expectedBelarusBankDtos1 = new ArrayDeque<>(List.of(belarusBankDto16));
        var expectedBelarusBankDtos2 = new ArrayDeque<>(List.of(belarusBankDto11));

        assertAll(() -> {
            assertThrows(RateNotFoundException.class,
                         () -> extractor.extract(belarusBankDtoList, userRequestParameters1, currencyAndDateStrategy));
            assertThrows(RateNotFoundException.class,
                         () -> extractor.extract(belarusBankDtoList, userRequestParameters2, currencyAndDateStrategy));
        });
    }

}