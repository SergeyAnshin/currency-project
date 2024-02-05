package org.good.job.currency.project.service;

public interface ExternalApiDtoMapper {

    <T> T responseBodyToExternalApiDto(String responseBody, Class<T> externalApiDtoClass);

}
