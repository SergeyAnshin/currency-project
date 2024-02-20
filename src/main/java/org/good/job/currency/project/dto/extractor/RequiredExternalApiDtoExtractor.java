package org.good.job.currency.project.dto.extractor;

import org.good.job.currency.project.dto.ArrayDto;
import org.good.job.currency.project.entity.UserRequestParametersData;

import java.util.ArrayDeque;


public interface RequiredExternalApiDtoExtractor {

    ArrayDeque<Object> extract(ArrayDto<?> externalApiRateDto, UserRequestParametersData userRequestParameters,
                               CheckStrategy checkStrategy);

}
