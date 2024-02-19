package org.good.job.currency.project.service;

public interface ExternalApiDtoMapper {

    Object responseBodyToExternalApiDto(String responseBody, Class<?> externalApiDtoClass);

}
