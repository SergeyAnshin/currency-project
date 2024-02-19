package org.good.job.currency.project.entity;

import lombok.*;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.annotation.UrlParameter;

import java.time.LocalDate;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserRequestParametersData {

    @UrlParameter(name = "externalApiName")
    private ExternalApiName externalApiName;
    @UrlParameter(name = "currencyCode")
    private String targetCurrencyCode;
    @UrlParameter(name = "localCurrencyCode")
    private String localCurrencyCode;
    @UrlParameter(name = "date")
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestParametersData that = (UserRequestParametersData) o;
        return externalApiName == that.externalApiName && Objects.equals(targetCurrencyCode, that.targetCurrencyCode)
                && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(externalApiName, targetCurrencyCode, date);
    }

}
