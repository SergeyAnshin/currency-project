package org.good.job.currency.project.controller;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.RateStatisticData;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Currency;


@RequiredArgsConstructor

@Validated

@RestController
@RequestMapping("/rates")
public class RateController {

    private final RateService rateService;

    @GetMapping
    public ResponseEntity<GeneralRate> getExternalApiCurrencyRateByDate(@RequestParam ExternalApiName externalApiName,
                                                                        @RequestParam Currency currency,
                                                                        @RequestParam @PastOrPresent LocalDate date) {
        var rate = rateService.getRateByExternalApiNameAndCurrencyAndDate(externalApiName, currency, date);
        return ResponseEntity.ok(rate);
    }

    @GetMapping("/statistics")
    public ResponseEntity<RateStatisticData> getCurrencyRateStatistics(@RequestParam ExternalApiName externalApiName,
                                                                       @RequestParam String currency,
                                                                       @RequestParam @Past LocalDate startDate,
                                                                       @RequestParam @PastOrPresent LocalDate endDate) {
        var rateStatistics = rateService.getStatistics(externalApiName, currency, startDate, endDate);
        return ResponseEntity.ok(rateStatistics);
    }

}
