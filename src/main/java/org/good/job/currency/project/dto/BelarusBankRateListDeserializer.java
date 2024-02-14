package org.good.job.currency.project.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.good.job.currency.project.dto.enums.ConstCurrency;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class BelarusBankRateListDeserializer extends JsonDeserializer<BelarusBankRateList> {


    @Override
    public BelarusBankRateList deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var treeNode = p.readValueAsTree();
        var belarusBankRates = ctxt.readTreeAsValue((JsonNode) treeNode, BelarusBankRate[].class);
        List<BelarusBankConvertedDto> belarusBankConvertedDtoList = new ArrayList<>();
        for (BelarusBankRate belarusBankRate : belarusBankRates) {
            belarusBankConvertedDtoList.addAll(convertBelarusBankDtoToList(belarusBankRate));
        }
        return BelarusBankRateList.builder().rates(belarusBankConvertedDtoList).build();
    }

    // TODO сделать разбор полей через рефлексию
    private List<BelarusBankConvertedDto> convertBelarusBankDtoToList(BelarusBankRate belarusBankRate) {
        List<BelarusBankConvertedDto> belarusBankConvertedDtoList = new ArrayList<>();
        belarusBankConvertedDtoList.add(BelarusBankConvertedDto.builder()
                                                .date(belarusBankRate.getDate())
                                                .sellCurrency(ConstCurrency.USD.toString())
                                                .sellRate(belarusBankRate.getSellUsd())
                                                .buyRate(belarusBankRate.getBuyUsd())
                                                .build());
        belarusBankConvertedDtoList.add(BelarusBankConvertedDto.builder()
                                                .date(belarusBankRate.getDate())
                                                .sellCurrency(ConstCurrency.EUR.toString())
                                                .sellRate(belarusBankRate.getSellEur())
                                                .buyRate(belarusBankRate.getBuyEur())
                                                .build());
        belarusBankConvertedDtoList.add(BelarusBankConvertedDto.builder()
                                                .date(belarusBankRate.getDate())
                                                .sellCurrency(ConstCurrency.RUB.toString())
                                                .sellRate(belarusBankRate.getSellRub())
                                                .buyRate(belarusBankRate.getBuyRub())
                                                .build());
        belarusBankConvertedDtoList.add(BelarusBankConvertedDto.builder()
                                                .date(belarusBankRate.getDate())
                                                .sellCurrency(ConstCurrency.CYN.toString())
                                                .sellRate(belarusBankRate.getSellCny())
                                                .buyRate(belarusBankRate.getBuyCny())
                                                .build());
        return belarusBankConvertedDtoList;
    }

}
