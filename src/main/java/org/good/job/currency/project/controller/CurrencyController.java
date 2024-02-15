package org.good.job.currency.project.controller;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.impl.CurrencyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor

@Validated

@RestController
@RequestMapping("/api/banks")
public class CurrencyController {

    private final CurrencyServiceImpl currencyService;

    @GetMapping("/{externalApiName}/currencies")
    public ResponseEntity<?> getAvailableCurrenciesByBankName(@PathVariable ExternalApiName externalApiName) {
        return ResponseEntity.ok(currencyService.getAvailableCurrenciesByExternalApiName(externalApiName));
    }

}
