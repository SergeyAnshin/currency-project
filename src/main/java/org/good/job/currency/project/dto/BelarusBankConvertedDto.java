package org.good.job.currency.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.good.job.currency.project.dto.enums.ConstCurrency;
import org.good.job.currency.project.dto.storage.annotation.AssignedExternalApiDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.good.job.currency.project.entity.enums.ExternalApiName.BELARUS_BANK;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@AssignedExternalApiDto(externalApi = BELARUS_BANK, currencyDto = BelarusBankDtoList.class,
        rateDto = BelarusBankDtoList.class, ratesByPeriodDto = BelarusBankDtoList.class)
public class BelarusBankConvertedDto extends GeneralExternalApiDto implements Checkable {

    private LocalDateTime date;
    private String sellCurrencyCode;
    private final String buyCurrencyCode = ConstCurrency.BYN.toString();
    private double sellRate;
    private double buyRate;

    @Override
    public int getSellCurrencyCodeId() {
        return 0;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BelarusBankConvertedDto that = (BelarusBankConvertedDto) o;
        return Double.compare(sellRate, that.sellRate) == 0
                && Double.compare(buyRate, that.buyRate) == 0 && Objects.equals(date, that.date)
                && Objects.equals(sellCurrencyCode, that.sellCurrencyCode) && Objects.equals(
                buyCurrencyCode, that.buyCurrencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, sellCurrencyCode, buyCurrencyCode, sellRate, buyRate);
    }

}
