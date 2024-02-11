package org.good.job.currency.project.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.ConfigProperties;
import org.good.job.currency.project.dto.AlfaBankRateList;
import org.good.job.currency.project.dto.BelarusBankRateList;
import org.good.job.currency.project.dto.NbrbRateDto;
import org.good.job.currency.project.entity.Property;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExternalApiProperty {
    NATIONAL_BANK_PROPERTY(Property.builder()
                                   .currencyDtoClass(NbrbRateDto.class)//TODO NbrbRateDtoList
                                   .rateDtoClass(NbrbRateDto.class)
                                   .build()),
    ALFA_BANK_PROPERTY(Property.builder()
                               .currencyDtoClass(AlfaBankRateList.class)
                               .rateDtoClass(AlfaBankRateList.class)
                               .build()),
    BELARUS_BANK_PROPERTY(Property.builder()
                                  .currencyDtoClass(BelarusBankRateList.class)
                                  .rateDtoClass(BelarusBankRateList.class)
                                  .build());

    private final Property property;
}
