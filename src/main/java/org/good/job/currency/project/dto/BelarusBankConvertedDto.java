package org.good.job.currency.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.good.job.currency.project.dto.enums.ConstCurrency;
import org.good.job.currency.project.dto.storage.annotation.AssignedExternalApiDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

}
