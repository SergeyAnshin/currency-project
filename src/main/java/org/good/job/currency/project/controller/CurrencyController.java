package org.good.job.currency.project.controller;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.ConfigProperties;
import org.good.job.currency.project.service.impl.CurrencyServiceImpl;
import org.good.job.currency.project.service.validation.annotation.SupportedBank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor

@Validated

@RestController
@RequestMapping("/api/banks")
public class CurrencyController {

    private final CurrencyServiceImpl currencyService;

    @GetMapping("/{bankName}/currencies")
    public ResponseEntity<?> getAvailableCurrenciesByBankName(@PathVariable /*@SupportedBank*/ String bankName) {
        return ResponseEntity.ok(currencyService.getAvailableCurrenciesByBankName(bankName));
    }

}
