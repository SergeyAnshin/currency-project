package org.good.job.currency.project.entity;

import lombok.*;
import org.good.job.currency.project.entity.enums.ExternalApiName;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
// TODO заменить на использование ExternalApiUrl из dao
public class ExternalApiUrl {

    private ExternalApiName externalApiName;
    private String currency;
    private LocalDate date;

}
