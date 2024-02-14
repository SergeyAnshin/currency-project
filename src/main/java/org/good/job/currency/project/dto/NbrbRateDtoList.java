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
@JsonDeserialize(using = NbrbCurrencyListDeserializer.class)
public class NbrbRateDtoList implements ArrayRate<NbrbRateDto>{


    private List<NbrbRateDto> rates;

    @Override
    public List<NbrbRateDto> getListDto() {
        return null;
    }

}
