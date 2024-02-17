package org.good.job.currency.project.controller;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.RateStatisticData;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.RateService;
import org.good.job.currency.project.service.validation.annotation.SupportedCurrencyCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RequiredArgsConstructor

@Validated

@RestController
@RequestMapping("/rates")
public class RateController {

    private final RateService rateService;

    @GetMapping
    public ResponseEntity<GeneralRate> getExternalApiCurrencyRateByDate(@RequestParam ExternalApiName externalApiName,
                                                                        @RequestParam @SupportedCurrencyCode
                                                                        String currencyCode,
                                                                        @RequestParam @PastOrPresent LocalDate date) {
        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(currencyCode)
                .localCurrencyCode("BYN")
                .date(date)
                .build();
        var rate = rateService.getRateByExternalApiNameAndCurrencyAndDate(userRequestParameters);
        return ResponseEntity.ok(rate);
    }

    @GetMapping("/statistics")
    public ResponseEntity<RateStatisticData> getCurrencyRateStatistics(@RequestParam ExternalApiName externalApiName,
                                                                       @RequestParam @SupportedCurrencyCode
                                                                       String currencyCode,
                                                                       @RequestParam @Past LocalDate startDate,
                                                                       @RequestParam @PastOrPresent LocalDate endDate) {
        var rateStatistics = rateService.getStatistics(externalApiName, currencyCode, startDate, endDate);
        return ResponseEntity.ok(rateStatistics);
    }

}
