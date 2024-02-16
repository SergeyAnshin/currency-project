package org.good.job.currency.project.controller;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.impl.CurrencyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Del Validated
 * Change CurrencyServiceImpl to interface
 * Change ResponseEntity<?> to  ResponseEntity<List<CurrencyCodeDto>>
 */
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
