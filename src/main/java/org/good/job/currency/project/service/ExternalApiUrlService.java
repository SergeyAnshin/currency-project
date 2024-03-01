package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.UserRequestParametersData;


public interface ExternalApiUrlService {

    String substituteParametersInUrl(UserRequestParametersData userRequestParameters, String externalApiUrl);

}
