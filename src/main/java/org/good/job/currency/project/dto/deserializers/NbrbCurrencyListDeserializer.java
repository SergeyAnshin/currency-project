package org.good.job.currency.project.dto.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.good.job.currency.project.dto.NationalBankDto;
import org.good.job.currency.project.dto.NationalBankDtoList;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Component
public class NbrbCurrencyListDeserializer extends JsonDeserializer<NationalBankDtoList> {

    @Override
    public NationalBankDtoList deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws
            IOException {
        var treeNode = jsonParser.readValueAsTree();
        var nbrbRates = deserializationContext.readTreeAsValue((JsonNode) treeNode, NationalBankDto[].class);
        return NationalBankDtoList.builder().rates(List.of(nbrbRates)).build();
    }

}
