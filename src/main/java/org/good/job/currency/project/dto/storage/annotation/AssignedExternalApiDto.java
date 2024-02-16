package org.good.job.currency.project.dto.storage.annotation;

import org.good.job.currency.project.entity.enums.ExternalApiName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface AssignedExternalApiDto {

    ExternalApiName externalApi();

    Class<?> rateDto();

    Class<?> currencyDto();

}
