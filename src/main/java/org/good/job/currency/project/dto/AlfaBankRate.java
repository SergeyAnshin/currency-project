package org.good.job.currency.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class AlfaBankRate extends GeneralExternalApiRate implements Checkable {

    private double sellRate;
    private String sellIso;
    private int sellCode;
    private double buyRate;
    private String buyIso;
    private int buyCode;
    private int quantity;
    private String name;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @Override
    public String getSellCurrencyCode() {
        return sellIso;
    }

    @Override
    public String getBuyCurrencyCode() {
        return buyIso;
    }

    @Override
    public LocalDate getDateOfRate() {
        return date;
    }

}
