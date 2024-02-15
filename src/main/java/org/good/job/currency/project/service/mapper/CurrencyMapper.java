package org.good.job.currency.project.service.mapper;

import org.good.job.currency.project.dto.*;
import org.good.job.currency.project.entity.CurrencyList;
import org.good.job.currency.project.entity.GeneralCurrency;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;

@Mapper
public interface CurrencyMapper {
    @SubclassMapping(target = CurrencyList.class, source = NationalBankDtoList.class)
    @SubclassMapping(target = CurrencyList.class, source = AlfaBankDtoList.class)
    @SubclassMapping(target = CurrencyList.class, source = BelarusBankDtoList.class)
    CurrencyList currencyDtoListToCurrencyList(Object externalApiCurrencyList);

    @SubclassMapping(target = GeneralCurrency.class, source = NationalBankDto.class)
    @SubclassMapping(target = GeneralCurrency.class, source = AlfaBankDto.class)
    @SubclassMapping(target = GeneralCurrency.class, source = BelarusBankConvertedDto.class)
    GeneralCurrency currencyDtoToCurrency(Object externalApiCurrency);

}
