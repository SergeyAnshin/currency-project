package org.good.job.currency.project.service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.RateService;
import org.good.job.currency.project.service.validation.annotation.SupportedCurrencyByExternalApi;
import org.springframework.stereotype.Component;

import java.util.Currency;


@RequiredArgsConstructor

@Component

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class SupportedExternalApiAndCurrencyValidator
        implements ConstraintValidator<SupportedCurrencyByExternalApi, Object[]> {

    private final RateService rateService;

    @Override
    public boolean isValid(Object[] bankAndCurrency, ConstraintValidatorContext context) {
        if (bankAndCurrency.length != 3) {
            throw new IllegalArgumentException("Illegal method signature");
        }

        if (bankAndCurrency[0] == null || bankAndCurrency[1] == null) {
            return true;
        }

        ExternalApiName externalApiName = extractExternalApiName(bankAndCurrency, ExternalApiName.class);
        Currency currency = extractExternalApiName(bankAndCurrency, Currency.class);
        return rateService.getAvailableCurrenciesByExternalApi(externalApiName).contains(currency);
    }

    private <T> T extractExternalApiName(Object[] bankAndCurrency, Class<T> aa) {
        if (bankAndCurrency[0].getClass().equals(aa)) {
            return (T) bankAndCurrency[0];
        } else if (bankAndCurrency[1].getClass().equals(aa)) {
            return (T) bankAndCurrency[1];
        }
        throw new IllegalArgumentException(
                "Illegal method signature, expected two parameters currency and external api");
    }

}
