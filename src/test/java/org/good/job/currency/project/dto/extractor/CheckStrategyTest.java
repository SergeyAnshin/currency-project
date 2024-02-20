package org.good.job.currency.project.dto.extractor;

import org.good.job.currency.project.dto.AlfaBankDto;
import org.good.job.currency.project.dto.BelarusBankConvertedDto;
import org.good.job.currency.project.dto.NationalBankDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;


@SpringBootTest
class CheckStrategyTest {

    private final CheckStrategy checkStrategy = spy(CheckStrategy.class);

    @Test
    void returnsTrueIfRateContainsRequiredCurrency() {
        var alfaBankCurrency = "EUR";
        var alfaBankDto = AlfaBankDto.builder().sellCurrencyCode(alfaBankCurrency).build();

        var belarusBankCurrency = "USD";
        var belarusBankDto = BelarusBankConvertedDto.builder().sellCurrencyCode(belarusBankCurrency).build();

        var nbrbCurrency = "BYN";
        var nbrbDto = NationalBankDto.builder().sellCurrencyCode(nbrbCurrency).build();

        assertAll(() -> {
            assertTrue(checkStrategy.isMatchingByCurrency(alfaBankDto, alfaBankCurrency));
            assertTrue(checkStrategy.isMatchingByCurrency(belarusBankDto, belarusBankCurrency));
            assertTrue(checkStrategy.isMatchingByCurrency(nbrbDto, nbrbCurrency));
        });
    }

    @Test
    void returnsFalseIfRateNotContainsRequiredCurrency() {
        var alfaBankCurrency = "EUR";
        var alfaBankDto = AlfaBankDto.builder().sellCurrencyCode(alfaBankCurrency).build();

        var belarusBankCurrency = "USD";
        var belarusBankDto = BelarusBankConvertedDto.builder().sellCurrencyCode(belarusBankCurrency).build();

        var nbrbCurrency = "BYN";
        var nbrbDto = NationalBankDto.builder().sellCurrencyCode(nbrbCurrency).build();

        assertAll(() -> {
            assertFalse(checkStrategy.isMatchingByCurrency(alfaBankDto, "USD"));
            assertFalse(checkStrategy.isMatchingByCurrency(belarusBankDto, "RUB"));
            assertFalse(checkStrategy.isMatchingByCurrency(nbrbDto, "EUR"));
        });
    }

    @Test
    void returnsTrueIfRateContainsRequiredDate() {
        var alfaBankDate = LocalDate.of(2024, 8, 22);
        var alfaBankDto = AlfaBankDto.builder().date(alfaBankDate).build();

        var belarusBankDtoDate = LocalDate.of(2023, 5, 17);
        var belarusBankDtoTime = LocalTime.of(12, 33, 15);
        var belarusBankDateTime = LocalDateTime.of(belarusBankDtoDate, belarusBankDtoTime);
        var belarusBankDto = BelarusBankConvertedDto.builder().date(belarusBankDateTime).build();

        var nbrbDtoDate = LocalDate.of(2024, 2, 13);
        var nbrbDtoTime = LocalTime.of(18, 53, 0);
        var nbrbDateTime = LocalDateTime.of(nbrbDtoDate, nbrbDtoTime);
        var nbrbDto = NationalBankDto.builder().date(nbrbDateTime).build();

        assertAll(() -> {
            assertTrue(checkStrategy.isMatchingByDate(alfaBankDto, alfaBankDate));
            assertTrue(checkStrategy.isMatchingByDate(belarusBankDto, belarusBankDtoDate));
            assertTrue(checkStrategy.isMatchingByDate(nbrbDto, nbrbDtoDate));
        });
    }

    @Test
    void returnsFalseIfRateNotContainsRequiredDate() {
        var alfaBankDate = LocalDate.of(2024, 8, 22);
        var alfaBankDto = AlfaBankDto.builder().date(alfaBankDate).build();

        var belarusBankDtoDate = LocalDate.of(2023, 5, 17);
        var belarusBankDtoTime = LocalTime.of(12, 33, 15);
        var belarusBankDateTime = LocalDateTime.of(belarusBankDtoDate, belarusBankDtoTime);
        var belarusBankDto = BelarusBankConvertedDto.builder().date(belarusBankDateTime).build();

        var nbrbDtoDate = LocalDate.of(2024, 2, 13);
        var nbrbDtoTime = LocalTime.of(18, 53, 0);
        var nbrbDateTime = LocalDateTime.of(nbrbDtoDate, nbrbDtoTime);
        var nbrbDto = NationalBankDto.builder().date(nbrbDateTime).build();

        assertAll(() -> {
            assertFalse(checkStrategy.isMatchingByDate(alfaBankDto, LocalDate.now()));
            assertFalse(checkStrategy.isMatchingByDate(belarusBankDto, LocalDate.of(2025, 12, 5)));
            assertFalse(checkStrategy.isMatchingByDate(nbrbDto, LocalDate.of(2020, 4, 8)));
        });
    }

    @Test
    void returnsTrueIfRateDateIsInPeriod() {
        var alfaBankDate = LocalDate.of(2024, 8, 22);
        var alfaBankDto = AlfaBankDto.builder().date(alfaBankDate).build();

        var belarusBankDtoDate = LocalDate.of(2023, 5, 17);
        var belarusBankDtoTime = LocalTime.of(12, 33, 15);
        var belarusBankDateTime = LocalDateTime.of(belarusBankDtoDate, belarusBankDtoTime);
        var belarusBankDto = BelarusBankConvertedDto.builder().date(belarusBankDateTime).build();

        var nbrbDtoDate = LocalDate.of(2024, 2, 13);
        var nbrbDtoTime = LocalTime.of(18, 53, 0);
        var nbrbDateTime = LocalDateTime.of(nbrbDtoDate, nbrbDtoTime);
        var nbrbDto = NationalBankDto.builder().date(nbrbDateTime).build();

        assertAll(() -> {
            assertTrue(checkStrategy.isMatchingByPeriod(alfaBankDto, alfaBankDate.minusMonths(1),
                                                        alfaBankDate.plusMonths(1)));
            assertTrue(checkStrategy.isMatchingByPeriod(alfaBankDto, alfaBankDate,
                                                        alfaBankDate.plusMonths(1)));
            assertTrue(checkStrategy.isMatchingByPeriod(belarusBankDto, belarusBankDtoDate.minusYears(1),
                                                        belarusBankDtoDate.plusDays(1)));
            assertTrue(checkStrategy.isMatchingByPeriod(belarusBankDto, belarusBankDtoDate.minusYears(1),
                                                        belarusBankDtoDate));
            assertTrue(checkStrategy.isMatchingByPeriod(nbrbDto, nbrbDtoDate.minusDays(1), nbrbDtoDate.plusDays(10)));
            assertTrue(checkStrategy.isMatchingByPeriod(nbrbDto, nbrbDtoDate, nbrbDtoDate));
        });
    }

    @Test
    void returnsFalseIfRateDateIsNotInPeriod() {
        var alfaBankDate = LocalDate.of(2024, 8, 22);
        var alfaBankDto = AlfaBankDto.builder().date(alfaBankDate).build();

        var belarusBankDtoDate = LocalDate.of(2023, 5, 17);
        var belarusBankDtoTime = LocalTime.of(12, 33, 15);
        var belarusBankDateTime = LocalDateTime.of(belarusBankDtoDate, belarusBankDtoTime);
        var belarusBankDto = BelarusBankConvertedDto.builder().date(belarusBankDateTime).build();

        var nbrbDtoDate = LocalDate.of(2024, 2, 13);
        var nbrbDtoTime = LocalTime.of(18, 53, 0);
        var nbrbDateTime = LocalDateTime.of(nbrbDtoDate, nbrbDtoTime);
        var nbrbDto = NationalBankDto.builder().date(nbrbDateTime).build();

        assertAll(() -> {
            assertFalse(checkStrategy.isMatchingByPeriod(alfaBankDto, alfaBankDate.minusYears(2),
                                                         alfaBankDate.minusYears(1)));
            assertFalse(checkStrategy.isMatchingByPeriod(belarusBankDto, belarusBankDtoDate.plusMonths(5),
                                                         belarusBankDtoDate.plusYears(1)));
        });
    }

}