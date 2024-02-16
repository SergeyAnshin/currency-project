package org.good.job.currency.project.service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.service.util.CurrencyUtils;
import org.good.job.currency.project.service.validation.annotation.SupportedCurrencyCode;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor

@Component
public class SupportedCurrencyCodeValidator implements ConstraintValidator<SupportedCurrencyCode, String> {

    @Override
    public boolean isValid(String currencyCode, ConstraintValidatorContext context) {
        return CurrencyUtils.isSupportedCurrency(currencyCode);
    }

}
