package org.good.job.currency.project.service;

import org.good.job.currency.project.dto.ArrayDto;
import org.good.job.currency.project.entity.ExternalApiUrl;


public interface RequiredExternalApiRateExtractor {

    Object extractFromRateList(ArrayDto<?> externalApiRateDto, ExternalApiUrl externalApiUrl);

}
