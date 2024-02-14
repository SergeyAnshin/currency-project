package org.good.job.currency.project.entity;

import lombok.*;

import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Property {

    private Class<?> currencyDtoClass;
    private Class<?> rateDtoClass;
    private String rateByCurrencyCodeAndDateCode;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property that = (Property) o;
        return Objects.equals(currencyDtoClass, that.currencyDtoClass)
                && Objects.equals(rateDtoClass, that.rateDtoClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyDtoClass, rateDtoClass);
    }

}
