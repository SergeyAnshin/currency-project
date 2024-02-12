package org.good.job.currency.project.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class GeneralRate {

    private double buyRate;
    private double sellRate;
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralRate that = (GeneralRate) o;
        return Double.compare(buyRate, that.buyRate) == 0
                && Double.compare(sellRate, that.sellRate) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyRate, sellRate, date);
    }

}
