package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.dto.AlfaBankDto;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.good.job.currency.project.entity.enums.ExternalApiName.ALFA_BANK;


@SpringBootTest
class AlfaBankRateCheckerTest {

    @Autowired
    private AlfaBankRateChecker rateChecker;

    @Test
    void isRateMatchParametersReturnFalseIfCurrencyCodesNotMatch() {
        var currentDate = LocalDate.now();

        var alfaBankDto = AlfaBankDto.builder()
                .sellRate(94.000000)
                .sellCurrencyCode("EUR")
                .sellCode(978)
                .buyRate(104.000000)
                .buyCurrencyCode("RUB")
                .buyCode(643)
                .quantity(1)
                .name(null)
                .date(currentDate)
                .build();

        var externalApiName = ALFA_BANK;
        var targetCurrencyCode = "JPY";
        var localCurrencyCode = "BYN";
        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(targetCurrencyCode)
                .localCurrencyCode(localCurrencyCode)
                .date(currentDate)
                .build();

        Assertions.assertFalse(rateChecker.isRateMatchParameters(alfaBankDto, userRequestParameters));
    }


    @Test
    void isRateMatchParametersReturnTrueIfCurrencyCodesMatch() {
        var externalApiName = ALFA_BANK;
        var targetCurrencyCode = "EUR";
        var localCurrencyCode = "RUB";
        var currentDate = LocalDate.now();

        var alfaBankDto = AlfaBankDto.builder()
                .sellRate(94.000000)
                .sellCurrencyCode(targetCurrencyCode)
                .sellCode(978)
                .buyRate(104.000000)
                .buyCurrencyCode(localCurrencyCode)
                .buyCode(643)
                .quantity(1)
                .name(null)
                .date(currentDate)
                .build();

        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(targetCurrencyCode)
                .localCurrencyCode(localCurrencyCode)
                .date(currentDate)
                .build();

        Assertions.assertTrue(rateChecker.isRateMatchParameters(alfaBankDto, userRequestParameters));
    }

    @Test
    void isRateMatchParametersReturnTrueIfDatesMatch() {
        var externalApiName = ALFA_BANK;
        var targetCurrencyCode = "EUR";
        var localCurrencyCode = "RUB";
        var currentDate = LocalDate.now();

        var alfaBankDto = AlfaBankDto.builder()
                .sellRate(94.000000)
                .sellCurrencyCode(targetCurrencyCode)
                .sellCode(978)
                .buyRate(104.000000)
                .buyCurrencyCode(localCurrencyCode)
                .buyCode(643)
                .quantity(1)
                .name(null)
                .date(currentDate)
                .build();

        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(targetCurrencyCode)
                .localCurrencyCode(localCurrencyCode)
                .date(currentDate)
                .build();

        Assertions.assertTrue(rateChecker.isRateMatchParameters(alfaBankDto, userRequestParameters));
    }

    @Test
    void isRateMatchParametersReturnFalseIfDatesNotMatch() {
        var externalApiName = ALFA_BANK;
        var targetCurrencyCode = "EUR";
        var localCurrencyCode = "RUB";
        var currentDate = LocalDate.now();

        var alfaBankDto = AlfaBankDto.builder()
                .sellRate(94.000000)
                .sellCurrencyCode(targetCurrencyCode)
                .sellCode(978)
                .buyRate(104.000000)
                .buyCurrencyCode(localCurrencyCode)
                .buyCode(643)
                .quantity(1)
                .name(null)
                .date(LocalDate.of(2024, 2, 13))
                .build();

        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(targetCurrencyCode)
                .localCurrencyCode(localCurrencyCode)
                .date(currentDate)
                .build();

        Assertions.assertFalse(rateChecker.isRateMatchParameters(alfaBankDto, userRequestParameters));
    }

    @Test
    void isRateMatchParametersReturnFalseIfLocalCurrencyNotMatch() {
        var externalApiName = ALFA_BANK;
        var targetCurrencyCode = "EUR";
        var localCurrencyCode = "BYN";
        var currentDate = LocalDate.now();

        var alfaBankDto = AlfaBankDto.builder()
                .sellRate(94.000000)
                .sellCurrencyCode(targetCurrencyCode)
                .sellCode(978)
                .buyRate(104.000000)
                .buyCurrencyCode("RUB")
                .buyCode(643)
                .quantity(1)
                .name(null)
                .date(LocalDate.of(2024, 2, 13))
                .build();

        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(targetCurrencyCode)
                .localCurrencyCode(localCurrencyCode)
                .date(currentDate)
                .build();

        Assertions.assertFalse(rateChecker.isRateMatchParameters(alfaBankDto, userRequestParameters));
    }



}