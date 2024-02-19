package org.good.job.currency.project.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Predicate;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExternalApiName {
    NATIONAL_BANK,
    BELARUS_BANK,
    ALFA_BANK;

    public static boolean isExternalApiNameExist(String externalApiName) {
        return Arrays.stream(values())
                .map(ExternalApiName::toString)
                .anyMatch(Predicate.isEqual(externalApiName.toUpperCase()));
    }

}
