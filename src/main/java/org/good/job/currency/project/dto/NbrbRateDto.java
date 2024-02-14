package org.good.job.currency.project.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.good.job.currency.project.dto.enums.ConstCurrency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class NbrbRateDto extends GeneralExternalApiRate implements Checkable {

    @JsonAlias({ "Cur_ID" })
    private long currencyId;

    @JsonAlias({ "Date" })
    private LocalDateTime date;

    @JsonAlias({ "Cur_Abbreviation" })
    private String sellCurrencyCode;

    @JsonAlias({ "Cur_Scale" })
    private int foreignCurrencyUnitsNumber;

    @JsonAlias({ "Cur_Name" })
    private String currencyName;

    @JsonAlias({ "Cur_OfficialRate" })
    private double sellRate;

    @Override
    public String getSellCurrencyCode() {
        return sellCurrencyCode;
    }

    @Override
    public String getBuyCurrencyCode() {
        return ConstCurrency.BYN.toString();
    }

    @Override
    public LocalDate getDateOfRate() {
        return LocalDate.from(date);
    }

    @Override
    public Currency getLocalCurrency() {
        return Currency.getInstance("BYN");
    }

}
