package org.good.job.currency.project.service;

import org.good.job.currency.project.dto.ArrayDto;
import org.good.job.currency.project.entity.UserRequestParametersData;


public interface RequiredExternalApiRateExtractor {

    Object extractFromRateList(ArrayDto<?> externalApiRateDto, UserRequestParametersData userRequestParameters);

}
