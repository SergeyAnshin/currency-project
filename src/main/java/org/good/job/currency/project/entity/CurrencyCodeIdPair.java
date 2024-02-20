package org.good.job.currency.project.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class CurrencyCodeIdPair {

    private String currencyCode;
    private int currencyExternalId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyCodeIdPair that = (CurrencyCodeIdPair) o;
        return currencyExternalId == that.currencyExternalId && Objects.equals(currencyCode, that.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCode, currencyExternalId);
    }

}
