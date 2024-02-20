package org.good.job.currency.project.service.util.testfile;

import org.good.job.currency.project.dto.AlfaBankDtoList;
import org.good.job.currency.project.dto.storage.annotation.AssignedExternalApiDto;

import static org.good.job.currency.project.entity.enums.ExternalApiName.ALFA_BANK;


@AssignedExternalApiDto(externalApi = ALFA_BANK, currencyDto = AlfaBankDtoList.class, rateDto = AlfaBankDtoList.class,
        ratesByPeriodDto = AlfaBankDtoList.class)
public class AnnotatedBankDto {
}
