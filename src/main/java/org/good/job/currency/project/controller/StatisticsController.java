package org.good.job.currency.project.controller;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dto.UserRequestParameters;
import org.good.job.currency.project.entity.RateStatisticData;
import org.good.job.currency.project.service.RateService;
import org.good.job.currency.project.service.validation.ExchangeRateByPeriodChecks;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final RateService rateService;

    @GetMapping
    public ResponseEntity<RateStatisticData> getCurrencyRateStatistics(
            @Validated(ExchangeRateByPeriodChecks.class) UserRequestParameters requestParameters) {
        RateStatisticData rateStatistics = rateService.getStatistics(requestParameters);
        return ResponseEntity.ok(rateStatistics);
    }

}
