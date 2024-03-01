package org.good.job.currency.project.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlfaBankDtoList that = (AlfaBankDtoList) o;
        return Objects.equals(dtoList, that.dtoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dtoList);
    }

}
