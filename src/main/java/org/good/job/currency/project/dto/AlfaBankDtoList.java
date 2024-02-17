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

    private List<AlfaBankDto> alfaBankDtos;

    @Override
    public List<AlfaBankDto> getListDto() {
        return alfaBankDtos;
    }

}
