package org.good.job.currency.project.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Currency;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class AlfaBankRate extends GeneralExternalApiRate implements Checkable {

    private double sellRate;
    @JsonAlias("sellIso")
    private String sellCurrencyCode;
    private int sellCode;
    private double buyRate;
    @JsonAlias("buyIso")
    private String buyCurrencyCode;
    private int buyCode;
    private int quantity;
    private String name;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @Override
    public String getSellCurrencyCode() {
        return sellCurrencyCode;
    }

    @Override
    public String getBuyCurrencyCode() {
        return buyCurrencyCode;
    }

    @Override
    public LocalDate getDateOfRate() {
        return date;
    }

    @Override
    public Currency getLocalCurrency() {
        return Currency.getInstance("BYN");
    }

}
