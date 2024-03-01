package org.good.job.currency.project.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.good.job.currency.project.entity.CurrencyCodeIdPair;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExternalApiName {
    NATIONAL_BANK(List.of(CurrencyCodeIdPair.builder().currencyCode("USD").currencyExternalId(431).build())),
    BELARUS_BANK(null),
    ALFA_BANK(null);

    private final List<CurrencyCodeIdPair> currencyCodeIdPairs;

    public static boolean isExternalApiNameExist(String externalApiName) {
        return Arrays.stream(values())
                .map(ExternalApiName::toString)
                .anyMatch(Predicate.isEqual(convertToUpperSnakeCase(externalApiName)));
    }

    private static String convertToUpperSnakeCase(String externalApiName) {
        return externalApiName.replaceAll(ProjectSymbol.WHITESPACE.getSymbol(), ProjectSymbol.UNDERSCORE.getSymbol())
                .toUpperCase();
    }

    public static ExternalApiName transformStringToEnum(String externalApiName) {
        return ExternalApiName.valueOf(convertToUpperSnakeCase(externalApiName));
    }

    public static Integer getExternalCurrencyId(ExternalApiName externalApiName, String currencyCode) {
        var pairs = externalApiName.getCurrencyCodeIdPairs();
        return nonNull(pairs) ? pairs.stream()
                .filter(pair -> Objects.equals(pair.getCurrencyCode(), currencyCode))
                .map(CurrencyCodeIdPair::getCurrencyExternalId)
                .findFirst()
                .orElseThrow() : -1;
    }

}
