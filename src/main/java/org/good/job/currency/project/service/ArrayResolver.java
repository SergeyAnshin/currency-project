package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.ExternalApiUrl;


public interface ArrayResolver {

    Object resolve(Object externalApiRateDto, ExternalApiUrl externalApiUrl);

}
