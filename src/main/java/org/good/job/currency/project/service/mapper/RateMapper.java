package org.good.job.currency.project.service.mapper;

import org.good.job.currency.project.dto.AlfaBankRate;
import org.good.job.currency.project.dto.BelarusBankConvertedDto;
import org.good.job.currency.project.dto.NbrbRateDto;
import org.good.job.currency.project.entity.ExternalApiUrlOld;
import org.good.job.currency.project.entity.GeneralRate;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;


@Mapper
public interface RateMapper {

    @SubclassMapping(target = GeneralRate.class, source = NbrbRateDto.class)
    @SubclassMapping(target = GeneralRate.class, source = AlfaBankRate.class)
    @SubclassMapping(target = GeneralRate.class, source = BelarusBankConvertedDto.class)
    GeneralRate externalApiRateDtoToRate(Object externalApiRateDto, ExternalApiUrlOld externalApiUrlOld);

}
