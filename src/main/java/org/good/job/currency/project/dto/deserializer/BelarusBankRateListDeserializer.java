package org.good.job.currency.project.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.good.job.currency.project.dto.BelarusBankConvertedDto;
import org.good.job.currency.project.dto.BelarusBankDto;
import org.good.job.currency.project.dto.BelarusBankDtoList;
import org.good.job.currency.project.dto.enums.ConstCurrency;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class BelarusBankRateListDeserializer extends JsonDeserializer<BelarusBankDtoList> {


    @Override
    public BelarusBankDtoList deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var treeNode = p.readValueAsTree();
        var belarusBankRates = ctxt.readTreeAsValue((JsonNode) treeNode, BelarusBankDto[].class);
        List<BelarusBankConvertedDto> belarusBankConvertedDtoList = new ArrayList<>();
        for (BelarusBankDto belarusBankDto : belarusBankRates) {
            belarusBankConvertedDtoList.addAll(convertBelarusBankDtoToList(belarusBankDto));
        }
        return BelarusBankDtoList.builder().rates(belarusBankConvertedDtoList).build();
    }

    // TODO сделать разбор полей через рефлексию
    private List<BelarusBankConvertedDto> convertBelarusBankDtoToList(BelarusBankDto belarusBankDto) {
        List<BelarusBankConvertedDto> belarusBankConvertedDtoList = new ArrayList<>();
        belarusBankConvertedDtoList.add(BelarusBankConvertedDto.builder()
                                                .date(belarusBankDto.getDate())
                                                .sellCurrencyCode(ConstCurrency.USD.toString())
                                                .sellRate(belarusBankDto.getSellUsd())
                                                .buyRate(belarusBankDto.getBuyUsd())
                                                .build());
        belarusBankConvertedDtoList.add(BelarusBankConvertedDto.builder()
                                                .date(belarusBankDto.getDate())
                                                .sellCurrencyCode(ConstCurrency.EUR.toString())
                                                .sellRate(belarusBankDto.getSellEur())
                                                .buyRate(belarusBankDto.getBuyEur())
                                                .build());
        belarusBankConvertedDtoList.add(BelarusBankConvertedDto.builder()
                                                .date(belarusBankDto.getDate())
                                                .sellCurrencyCode(ConstCurrency.RUB.toString())
                                                .sellRate(belarusBankDto.getSellRub())
                                                .buyRate(belarusBankDto.getBuyRub())
                                                .build());
        belarusBankConvertedDtoList.add(BelarusBankConvertedDto.builder()
                                                .date(belarusBankDto.getDate())
                                                .sellCurrencyCode(ConstCurrency.CNY.toString())
                                                .sellRate(belarusBankDto.getSellCny())
                                                .buyRate(belarusBankDto.getBuyCny())
                                                .build());
        return belarusBankConvertedDtoList;
    }

}
