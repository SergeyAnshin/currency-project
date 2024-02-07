package org.good.job.currency.project.service.mapper;

import org.good.job.currency.project.dto.BelarusBankRate;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.service.util.ClassFieldRateExtractor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;


@Mapper
public interface BelarusBankRateMapper {

    default GeneralRate externalApiRateDtoToRate(BelarusBankRate externalApiRateDto,
                                                 @Context ExternalApiUrl externalApiUrl) {
        String currencyCode = externalApiUrl.getCurrency().getCurrencyCode();
        var buyRate = ClassFieldRateExtractor.getBuyRateByCurrencyCode(externalApiRateDto, currencyCode);
        var sellRate = ClassFieldRateExtractor.getSellRateByCurrencyCode(externalApiRateDto, currencyCode);
        return GeneralRate.builder().buyRate(buyRate).sellRate(sellRate).build();
    }

}
