package org.good.job.currency.project.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class AlfaBankDtoList implements ArrayDto<AlfaBankDto> {

    List<AlfaBankDto> rates;

    @Override
    public List<AlfaBankDto> getListDto() {
        return rates;
    }

}
