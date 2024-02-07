package org.good.job.currency.project.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@JsonDeserialize(using = BelarusBankRateListDeserializer.class)
public class BelarusBankRateList implements ArrayRate<BelarusBankRate> {

    List<BelarusBankRate> rates;

    @Override
    public List<BelarusBankRate> getListDto() {
        return rates;
    }


}
