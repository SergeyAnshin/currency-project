package org.good.job.currency.project.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
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

    @JsonAlias({"rates"})
    private List<AlfaBankDto> dtoList;

    @Override
    public List<AlfaBankDto> getListDto() {
        return dtoList;
    }

}
