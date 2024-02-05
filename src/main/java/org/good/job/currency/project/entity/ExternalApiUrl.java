package org.good.job.currency.project.entity;

import lombok.*;
import org.good.job.currency.project.entity.enums.ExternalApiName;

import java.time.LocalDate;
import java.util.Currency;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ExternalApiUrl {

    private ExternalApiName externalApiName;
    private Currency currency;
    private LocalDate date;

}
