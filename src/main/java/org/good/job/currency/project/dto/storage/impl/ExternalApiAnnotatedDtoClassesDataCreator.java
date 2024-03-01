package org.good.job.currency.project.dto.storage.impl;

import org.good.job.currency.project.dto.storage.ExternalApiDtoClassesData;
import org.good.job.currency.project.dto.storage.ExternalApiDtoClassesDataCreator;
import org.good.job.currency.project.dto.storage.annotation.AssignedExternalApiDto;
import org.springframework.stereotype.Service;


@Service
public class ExternalApiAnnotatedDtoClassesDataCreator
        implements ExternalApiDtoClassesDataCreator<AssignedExternalApiDto> {

    @Override
    public ExternalApiDtoClassesData create(AssignedExternalApiDto annotation) {
        return ExternalApiDtoClassesData.builder()
                .rateDto(annotation.rateDto())
                .currencyDto(annotation.currencyDto())
                .ratesByPeriodDto(annotation.ratesByPeriodDto())
                .build();
    }


}
