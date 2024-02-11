package org.good.job.currency.project.service.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.good.job.currency.project.service.validation.SupportedBankValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Constraint(validatedBy = SupportedBankValidator.class)
@Target({METHOD, CONSTRUCTOR, ANNOTATION_TYPE, PARAMETER, TYPE_PARAMETER })
@Retention(RUNTIME)
public @interface SupportedBank {
    String message() default "Bank is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
