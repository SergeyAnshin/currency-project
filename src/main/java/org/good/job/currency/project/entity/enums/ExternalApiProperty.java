package org.good.job.currency.project.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.good.job.currency.project.dto.AlfaBankDtoList;
import org.good.job.currency.project.dto.BelarusBankDtoList;
import org.good.job.currency.project.dto.NationalBankDto;
import org.good.job.currency.project.dto.NationalBankDtoList;
import org.good.job.currency.project.entity.Property;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExternalApiProperty {
    NATIONAL_BANK_PROPERTY(Property.builder()
                                   .currencyByExternalApiName("external-api.url.currency.by-name.nbrb")
                                   .rateByCurrencyCodeAndDateCode(
                                           "external-api.url.rate.by-currency-code-and-date.nbrb")
                                   .build()),
    ALFA_BANK_PROPERTY(Property.builder()
                               .currencyByExternalApiName("external-api.url.currency.by-name.alfa-bank")
                               .rateByCurrencyCodeAndDateCode(
                                       "external-api.url.rate.by-currency-code-and-date.alfa-bank")
                               .build()),
    BELARUS_BANK_PROPERTY(Property.builder()
                                  .currencyByExternalApiName("external-api.url.currency.by-name.belarus-bank")
                                  .rateByCurrencyCodeAndDateCode(
                                          "external-api.url.rate.by-currency-code-and-date.belarus-bank")
                                  .build());

    private final Property property;
}
