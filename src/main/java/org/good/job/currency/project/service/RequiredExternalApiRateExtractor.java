package org.good.job.currency.project.service;

import org.good.job.currency.project.dto.ArrayRate;
import org.good.job.currency.project.entity.ExternalApiUrlOld;


public interface RequiredExternalApiRateExtractor {

    Object extractFromRateList(ArrayRate<?> externalApiRateDto, ExternalApiUrlOld externalApiUrlOld);

}
