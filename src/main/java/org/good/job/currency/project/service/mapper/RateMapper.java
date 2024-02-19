package org.good.job.currency.project.service.mapper;

import org.good.job.currency.project.dto.AlfaBankDto;
import org.good.job.currency.project.dto.BelarusBankConvertedDto;
import org.good.job.currency.project.dto.NationalBankDto;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;


@Mapper
public interface RateMapper {

    @SubclassMapping(target = GeneralRate.class, source = NationalBankDto.class)
    @SubclassMapping(target = GeneralRate.class, source = AlfaBankDto.class)
    @SubclassMapping(target = GeneralRate.class, source = BelarusBankConvertedDto.class)
    GeneralRate externalApiRateDtoToRate(Object externalApiRateDto, UserRequestParametersData userRequestParameters);

}
