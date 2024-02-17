package org.good.job.currency.project.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.service.ExternalApiDtoMapper;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor

@Service
public class ExternalApiDtoObjectMapper implements ExternalApiDtoMapper {

    private final ObjectMapper objectMapper;

    public Object responseBodyToExternalApiDto(String responseBody, Class<?> externalApiDtoClass) {
        try {
            return objectMapper.readValue(responseBody, externalApiDtoClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
