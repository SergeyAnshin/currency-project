package org.good.job.currency.project.service.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.good.job.currency.project.service.validation.SupportedCurrencyCodeValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Constraint(validatedBy = SupportedCurrencyCodeValidator.class)
@Target({ METHOD, CONSTRUCTOR, ANNOTATION_TYPE, PARAMETER, TYPE_PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface SupportedCurrencyCode {

    String message() default "{org.hibernate.validator.referenceguide.chapter04.crossparameter.SupportedCurrencyCode.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
