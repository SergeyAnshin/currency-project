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


@RequiredArgsConstructor

@Validated

@RestController
@RequestMapping("/api/banks")
public class CurrencyController {
    private final CurrencyServiceImpl currencyService;

    @GetMapping("/{bankName}/currencies")
    public ResponseEntity<?> getAvailableCurrenciesByBankName(@PathVariable ExternalApiName bankName){
        return ResponseEntity.ok(currencyService.getAvailableCurrenciesByExternalApi(bankName));
    }
}
