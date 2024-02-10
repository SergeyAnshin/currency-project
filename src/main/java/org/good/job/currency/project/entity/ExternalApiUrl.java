package org.good.job.currency.project.entity;

import lombok.*;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.annotations.UrlParameter;

import java.time.LocalDate;
import java.util.Currency;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ExternalApiUrl {
    @UrlParameter(name = "apiName")
    private ExternalApiName externalApiName;
    @UrlParameter(name = "currencyCode")
    private Currency currency;
    @UrlParameter(name = "date")
    private LocalDate date;

}
