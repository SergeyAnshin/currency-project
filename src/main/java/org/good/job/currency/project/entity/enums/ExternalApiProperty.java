package org.good.job.currency.project.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.good.job.currency.project.dto.AlfaBankRateList;
import org.good.job.currency.project.dto.BelarusBankRateList;
import org.good.job.currency.project.dto.NbrbRateDto;
import org.good.job.currency.project.dto.NbrbRateDtoList;
import org.good.job.currency.project.entity.Property;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExternalApiProperty {
    NATIONAL_BANK_PROPERTY(Property.builder()
                                   .currencyDtoClass(NbrbRateDtoList.class)
                                   .rateDtoClass(NbrbRateDto.class)
                                   .rateByCurrencyCodeAndDateCode(
                                           "external-api.url.rate.by-currency-code-and-date.nbrb")
                                   .build()),
    ALFA_BANK_PROPERTY(Property.builder()
                               .currencyDtoClass(AlfaBankRateList.class)
                               .rateDtoClass(AlfaBankRateList.class)
                               .rateByCurrencyCodeAndDateCode(
                                       "external-api.url.rate.by-currency-code-and-date.alfa-bank")
                               .build()),
    BELARUS_BANK_PROPERTY(Property.builder()
                                  .currencyDtoClass(BelarusBankRateList.class)
                                  .rateDtoClass(BelarusBankRateList.class)
                                  .rateByCurrencyCodeAndDateCode(
                                          "external-api.url.rate.by-currency-code-and-date.belarus-bank")
                                  .build());

    private final Property property;
}
