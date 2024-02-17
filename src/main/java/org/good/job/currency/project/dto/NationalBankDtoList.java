package org.good.job.currency.project.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.good.job.currency.project.dto.deserializer.NbrbCurrencyListDeserializer;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@JsonDeserialize(using = NbrbCurrencyListDeserializer.class)
public class NationalBankDtoList implements ArrayDto<NationalBankDto> {


    private List<NationalBankDto> dtoList;

    @Override
    public List<NationalBankDto> getListDto() {
        return dtoList;
    }

}
