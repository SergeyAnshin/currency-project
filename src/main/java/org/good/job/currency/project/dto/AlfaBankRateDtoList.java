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

public class AlfaBankRateDtoList extends GeneralExternalApiRate {

    List<AlfaBankRate> rates;

}
