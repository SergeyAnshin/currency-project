package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.service.ExternalApiCaller;
import org.good.job.currency.project.service.exception.ExternalApiProblemException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor

@Service
public class RestTemplateCaller implements ExternalApiCaller {

    @Override
    public String call(String externalApiUrl) {
        var restTemplate = new RestTemplate();
        var responseEntity = restTemplate.getForEntity(externalApiUrl, String.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new ExternalApiProblemException();
        }
    }

}
