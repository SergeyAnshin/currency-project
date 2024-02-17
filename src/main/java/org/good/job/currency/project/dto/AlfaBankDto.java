package org.good.job.currency.project.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.good.job.currency.project.dto.storage.annotation.AssignedExternalApiDto;

import java.time.LocalDate;
import java.util.Currency;

import static org.good.job.currency.project.entity.enums.ExternalApiName.ALFA_BANK;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString

@AssignedExternalApiDto(externalApi = ALFA_BANK, currencyDto = AlfaBankDtoList.class, rateDto = AlfaBankDtoList.class)
public class AlfaBankDto extends GeneralExternalApiDto implements Checkable {

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

}
