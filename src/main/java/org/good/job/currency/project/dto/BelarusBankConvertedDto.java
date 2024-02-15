package org.good.job.currency.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.good.job.currency.project.dto.enums.ConstCurrency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BelarusBankConvertedDto extends GeneralExternalApiDto implements Checkable {
    private LocalDateTime date;
    private String sellCurrencyCode;
    private final String buyCurrencyCode = ConstCurrency.BYN.toString();
    private double sellRate;
    private double buyRate;

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
        return LocalDate.from(date);
    }

    @Override
    public Currency getLocalCurrency() {
        return Currency.getInstance(buyCurrencyCode);
    }

}
