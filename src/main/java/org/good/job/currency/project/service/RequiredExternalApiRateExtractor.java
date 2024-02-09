package org.good.job.currency.project.service;

import org.good.job.currency.project.dto.ArrayRate;
import org.good.job.currency.project.entity.ExternalApiUrl;


public interface RequiredExternalApiRateExtractor {

    Object extractFromRateList(ArrayRate<?> externalApiRateDto, ExternalApiUrl externalApiUrl);

}
