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
public class AlfaBankRateList implements ArrayRate<AlfaBankRate> {

    List<AlfaBankRate> rates;

    @Override
    public List<AlfaBankRate> getListDto() {
        return rates;
    }

}
