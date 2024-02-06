package org.good.job.currency.project.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.impl.RateService;
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

    private final RateService rateService;

    @GetMapping
    public ResponseEntity<GeneralRate> getExternalApiCurrencyRateByDate(@RequestParam ExternalApiName externalApiName,
                                                                        @RequestParam Currency currency,
                                                                        @RequestParam @Valid @PastOrPresent LocalDate date) {
        var rate = rateService.getRateByExternalApiNameAndCurrencyAndDate(externalApiName, currency, date);
        return ResponseEntity.ok(rate);
    }

}
