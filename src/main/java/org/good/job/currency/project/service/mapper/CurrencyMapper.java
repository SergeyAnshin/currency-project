package org.good.job.currency.project.service.mapper;

import org.good.job.currency.project.dto.*;
import org.good.job.currency.project.entity.CurrencyList;
import org.good.job.currency.project.entity.GeneralCurrency;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;

@Mapper
public interface CurrencyMapper {
    @SubclassMapping(target = CurrencyList.class, source = NbrbRateDtoList.class)
    @SubclassMapping(target = CurrencyList.class, source = AlfaBankRateList.class)
    @SubclassMapping(target = CurrencyList.class, source = BelarusBankRateList.class)
    CurrencyList currencyDtoListToCurrencyList(Object externalApiCurrencyList);

    @SubclassMapping(target = GeneralCurrency.class, source = NbrbRateDto.class)
    @SubclassMapping(target = GeneralCurrency.class, source = AlfaBankRate.class)
    @SubclassMapping(target = GeneralCurrency.class, source = BelarusBankRate.class)
    GeneralCurrency currencyDtoToCurrency(Object externalApiCurrency);

}
