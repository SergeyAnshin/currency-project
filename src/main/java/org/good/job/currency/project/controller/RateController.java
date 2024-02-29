package org.good.job.currency.project.controller;


import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dto.enums.ConstCurrency;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.RateStatisticData;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.RateService;
import org.good.job.currency.project.service.validation.annotation.SupportedCurrencyCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor

@Validated

@RestController
@RequestMapping("/rates")
public class RateController {

    private final RateService rateService;

    @GetMapping
    public ResponseEntity<GeneralRate> getExternalApiCurrencyRateByDate(@RequestParam String externalApiName,
                                                                        @RequestParam @SupportedCurrencyCode
                                                                        String currencyCode,
                                                                        @RequestParam @PastOrPresent LocalDate date) {
        if (!ExternalApiName.isExternalApiNameExist(externalApiName)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(ExternalApiName.transformStringToEnum(externalApiName))
                .targetCurrencyCode(currencyCode)
                .localCurrencyCode(ConstCurrency.BYN.name())
                .date(date)
                .build();
        var rate = rateService.getRateByExternalApiNameAndCurrencyAndDate(userRequestParameters);
        return ResponseEntity.ok(rate);
    }

    @GetMapping(params = { "externalApiName", "currencyCode", "startDate", "endDate" })
    public ResponseEntity<List<GeneralRate>> getCurrencyRatesByPeriod(@RequestParam ExternalApiName externalApiName,
                                                                      @RequestParam @SupportedCurrencyCode
                                                                      String currencyCode,
                                                                      @RequestParam @Past LocalDate startDate,
                                                                      @RequestParam @PastOrPresent LocalDate endDate) {
        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(externalApiName)
                .targetCurrencyCode(currencyCode)
                .localCurrencyCode(ConstCurrency.BYN.name())
                .periodStartDate(startDate)
                .periodEndDate(endDate)
                .build();
        var ratesByPeriod = rateService.getCurrencyRatesByPeriod(userRequestParameters);
        return ResponseEntity.ok(ratesByPeriod);
    }

    @GetMapping("/statistics")
    public ResponseEntity<RateStatisticData> getCurrencyRateStatistics(@RequestParam String externalApiName,
                                                                       @RequestParam @SupportedCurrencyCode
                                                                       String currencyCode,
                                                                       @RequestParam @Past LocalDate startDate,
                                                                       @RequestParam @PastOrPresent LocalDate endDate) {
        if (!ExternalApiName.isExternalApiNameExist(externalApiName)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var userRequestParameters = UserRequestParametersData.builder()
                .externalApiName(ExternalApiName.transformStringToEnum(externalApiName))
                .targetCurrencyCode(currencyCode)
                .localCurrencyCode(ConstCurrency.BYN.name())
                .periodStartDate(startDate)
                .periodEndDate(endDate)
                .build();
        var rateStatistics = rateService.getStatistics(userRequestParameters);
        return ResponseEntity.ok(rateStatistics);
    }

}
