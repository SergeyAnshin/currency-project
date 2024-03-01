package org.good.job.currency.project.service.util.testfile;

import org.good.job.currency.project.dto.BelarusBankConvertedDto;
import org.good.job.currency.project.dto.BelarusBankDtoList;
import org.good.job.currency.project.dto.storage.annotation.AssignedExternalApiDto;

import static org.good.job.currency.project.entity.enums.ExternalApiName.BELARUS_BANK;


@AssignedExternalApiDto(externalApi = BELARUS_BANK, currencyDto = BelarusBankConvertedDto.class,
        rateDto = BelarusBankConvertedDto.class, ratesByPeriodDto = BelarusBankDtoList.class)
public class AnnotatedCryptoBankDto {
}
