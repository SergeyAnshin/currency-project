package org.good.job.currency.project.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.good.job.currency.project.entity.enums.ExternalApiRateProperty.*;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExternalApiName {
    NATIONAL_BANK(NATIONAL_BANK_RATE_PROPERTY),
    BELARUS_BANK(BELARUS_BANK_RATE_PROPERTY),
    ALFA_BANK(ALFA_BANK_RATE_PROPERTY);

    private final ExternalApiRateProperty externalApiRateProperty;

    public static boolean isExternalApiNameExist(String externalApiName) {
        return Arrays.stream(values())
                .map(ExternalApiName::toString)
                .anyMatch(Predicate.isEqual(externalApiName.toUpperCase()));
    }

}
