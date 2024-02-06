package org.good.job.currency.project.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.good.job.currency.project.dto.AlfaBankRateList;
import org.good.job.currency.project.dto.BelarusBankRateList;
import org.good.job.currency.project.dto.NbrbRateDto;
import org.good.job.currency.project.entity.RateProperty;


//TODO Переделать
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExternalApiRateProperty {
    NATIONAL_BANK_RATE_PROPERTY(RateProperty.builder()
                                        .rateByCurrencyCodeAndDateCode(
                                                "external-api.url.rate.by-currency-code-and-date.nbrb")
                                        .dtoClass(NbrbRateDto.class)
                                        .build()),
    ALFA_BANK_RATE_PROPERTY(RateProperty.builder()
                                    .rateByCurrencyCodeAndDateCode(
                                            "external-api.url.rate.by-currency-code-and-date.alfa-bank")
                                    .dtoClass(AlfaBankRateList.class)
                                    .build()),
    BELARUS_BANK_RATE_PROPERTY(RateProperty.builder()
                                       .rateByCurrencyCodeAndDateCode(
                                               "external-api.url.rate.by-currency-code-and-date.belarus-bank")
                                       .dtoClass(BelarusBankRateList.class)
                                       .build());

    private final RateProperty rateProperty;

}
