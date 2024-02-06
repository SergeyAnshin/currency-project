package org.good.job.currency.project.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public abstract class GeneralExternalApiRate extends ExternalApiDto {

    public abstract String getCurrencyCode();
    public abstract LocalDate getDateOfRate();

}
