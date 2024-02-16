package org.good.job.currency.project.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.good.job.currency.project.dto.enums.ConstCurrency;
import org.good.job.currency.project.dto.storage.annotation.AssignedExternalApiDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.good.job.currency.project.entity.enums.ExternalApiName.NATIONAL_BANK;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString

@AssignedExternalApiDto(externalApi = NATIONAL_BANK, currencyDto = NationalBankDtoList.class,
        rateDto = NationalBankDto.class)
public class NationalBankDto extends GeneralExternalApiDto implements Checkable {

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
