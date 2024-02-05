package org.good.job.currency.project.entity;

import lombok.*;

import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class RateProperty {

    private String rateByCurrencyCodeAndDateCode;
    private Class<?> dtoClass;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateProperty that = (RateProperty) o;
        return Objects.equals(rateByCurrencyCodeAndDateCode, that.rateByCurrencyCodeAndDateCode)
                && Objects.equals(dtoClass, that.dtoClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rateByCurrencyCodeAndDateCode, dtoClass);
    }

}
