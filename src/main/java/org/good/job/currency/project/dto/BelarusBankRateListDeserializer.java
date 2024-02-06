package org.good.job.currency.project.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Component
public class BelarusBankRateListDeserializer extends JsonDeserializer<BelarusBankRateList> {


    @Override
    public BelarusBankRateList deserialize(JsonParser p, DeserializationContext ctxt) throws IOException,
            JacksonException {
        var treeNode = p.readValueAsTree();
        var belarusBankRates = ctxt.readTreeAsValue((JsonNode) treeNode, BelarusBankRate[].class);
        return BelarusBankRateList.builder().rates(List.of(belarusBankRates)).build();
    }

}
