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
public class GeneralRate {

    private double currencyRate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralRate that = (GeneralRate) o;
        return Double.compare(currencyRate, that.currencyRate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyRate);
    }

}
