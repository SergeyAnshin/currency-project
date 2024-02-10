package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.ExternalApiUrl;

import java.util.Map;


public interface ExternalApiUrlParameterConverter {

    Map<String, String> convert(ExternalApiUrl parameters);
}
