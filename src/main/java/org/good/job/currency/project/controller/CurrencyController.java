package org.good.job.currency.project.controller;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor

@RestController
@RequestMapping("/api/banks")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/{externalApiName}/currencies")
    public ResponseEntity<List<String>> getAvailableCurrenciesByBankName(@PathVariable String externalApiName) {
        if (!ExternalApiName.isExternalApiNameExist(externalApiName)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(currencyService.getAvailableCurrenciesByExternalApiName(
                ExternalApiName.transformStringToEnum(externalApiName)));
    }

}
