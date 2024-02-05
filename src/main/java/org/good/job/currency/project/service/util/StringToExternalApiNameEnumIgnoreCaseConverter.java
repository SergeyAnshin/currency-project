package org.good.job.currency.project.service.util;

import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.exception.ExternalApiNameNotExistsException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static org.good.job.currency.project.entity.enums.ExternalApiName.isExternalApiNameExist;


@Component
public class StringToExternalApiNameEnumIgnoreCaseConverter implements Converter<String, ExternalApiName> {

    @Override
    public ExternalApiName convert(String externalApiName) {
        if (!externalApiName.isBlank() && isExternalApiNameExist(externalApiName)) {
            return ExternalApiName.valueOf(externalApiName.toUpperCase());
        } else {
            throw new ExternalApiNameNotExistsException();
        }
    }

}
