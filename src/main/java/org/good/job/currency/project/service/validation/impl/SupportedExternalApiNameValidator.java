package org.good.job.currency.project.service.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.service.ExternalApiService;
import org.good.job.currency.project.service.validation.annotation.SupportedExternalApiName;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor

@Component
public class SupportedExternalApiNameValidator implements ConstraintValidator<SupportedExternalApiName, String> {

    private final ExternalApiService externalApiService;

    @Override
    public boolean isValid(String ExternalApiName, ConstraintValidatorContext context) {
        return externalApiService.isExistsByName(ExternalApiName);
    }

}
