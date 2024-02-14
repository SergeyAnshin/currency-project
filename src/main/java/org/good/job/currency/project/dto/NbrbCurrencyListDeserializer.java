package org.good.job.currency.project.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Component
public class NbrbCurrencyListDeserializer extends JsonDeserializer<NbrbRateDtoList> {

    @Override
    public NbrbRateDtoList deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws
            IOException {
        var treeNode = jsonParser.readValueAsTree();
        var nbrbRates = deserializationContext.readTreeAsValue((JsonNode) treeNode, NbrbRateDto[].class);
        return NbrbRateDtoList.builder().rates(List.of(nbrbRates)).build();
    }

}
