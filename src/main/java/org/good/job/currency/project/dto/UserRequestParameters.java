package org.good.job.currency.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.good.job.currency.project.service.validation.ExchangeRateByDateChecks;
import org.good.job.currency.project.service.validation.ExchangeRateByPeriodChecks;
import org.good.job.currency.project.service.validation.annotation.SupportedCurrencyCode;
import org.good.job.currency.project.service.validation.annotation.SupportedExternalApiName;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserRequestParameters {

    @NotBlank(groups = { ExchangeRateByPeriodChecks.class, ExchangeRateByDateChecks.class })
    @SupportedExternalApiName(groups = { ExchangeRateByPeriodChecks.class, ExchangeRateByDateChecks.class })
    private String externalApiName;

    @NotBlank(groups = { ExchangeRateByPeriodChecks.class, ExchangeRateByDateChecks.class })
    @SupportedCurrencyCode(groups = { ExchangeRateByPeriodChecks.class, ExchangeRateByDateChecks.class })
    private String targetCurrencyCode;

    @NotBlank(groups = { ExchangeRateByPeriodChecks.class, ExchangeRateByDateChecks.class })
    @SupportedCurrencyCode(groups = { ExchangeRateByPeriodChecks.class, ExchangeRateByDateChecks.class })
    private String localCurrencyCode;

    @NotNull(groups = ExchangeRateByDateChecks.class)
    @PastOrPresent(groups = ExchangeRateByDateChecks.class)
    private LocalDate exchangeRateDate;

    @NotNull(groups = ExchangeRateByPeriodChecks.class)
    @Past(groups = ExchangeRateByPeriodChecks.class)
    private LocalDate periodStartDate;

    @NotNull(groups = ExchangeRateByPeriodChecks.class)
    @PastOrPresent(groups = ExchangeRateByPeriodChecks.class)
    private LocalDate periodEndDate;

}
