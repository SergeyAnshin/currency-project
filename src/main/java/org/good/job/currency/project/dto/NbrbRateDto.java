package org.good.job.currency.project.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class NbrbRateDto extends GeneralExternalApiRate {

    @JsonAlias({ "Cur_ID" })
    private long currencyId;

    @JsonAlias({ "Date" })
    private LocalDateTime date;

    @JsonAlias({ "Cur_Abbreviation" })
    private String currencyAbbreviation;

    @JsonAlias({ "Cur_Scale" })
    private int foreignCurrencyUnitsNumber;

    @JsonAlias({ "Cur_Name" })
    private String currencyName;

    @JsonAlias({ "Cur_OfficialRate" })
    private double sellRate;

    @Override
    public String getCurrencyCode() {
        return currencyAbbreviation;
    }

    @Override
    public LocalDate getDateOfRate() {
        return LocalDate.from(date);
    }

}
