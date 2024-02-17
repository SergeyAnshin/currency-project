package org.good.job.currency.project.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.good.job.currency.project.dto.deserializer.BelarusBankRateListDeserializer;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@JsonDeserialize(using = BelarusBankRateListDeserializer.class)
public class BelarusBankDtoList implements ArrayDto<BelarusBankConvertedDto> {

    private List<BelarusBankConvertedDto> dtoList;

    @Override
    public List<BelarusBankConvertedDto> getListDto() {
        return dtoList;
    }

}
