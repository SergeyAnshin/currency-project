package org.good.job.currency.project.controller;

import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.impl.RateServiceImpl;
import org.good.job.currency.project.service.validation.annotation.SupportedCurrencyByExternalApi;
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
@RequestMapping("/rate")
public class RateController {

    private final RateServiceImpl rateServiceImpl;

    @GetMapping
    public ResponseEntity<GeneralRate> getExternalApiCurrencyRateByDate(@RequestParam ExternalApiName externalApiName,
                                                                        @RequestParam Currency currency,
                                                                        @RequestParam @PastOrPresent LocalDate date) {
        var rate = rateServiceImpl.getRateByExternalApiNameAndCurrencyAndDate(externalApiName, currency, date);
        return ResponseEntity.ok(rate);
    }

}
