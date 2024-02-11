package org.good.job.currency.project.service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.ConfigProperties;
import org.good.job.currency.project.service.validation.annotation.SupportedBank;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor

@Component

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class SupportedBankValidator implements ConstraintValidator<SupportedBank, String> {

    private final ConfigProperties configProperties;

    @Override
    public void initialize(SupportedBank constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null
                && !s.isBlank()
                && configProperties.getBanks().get(s) != null;
    }

}
