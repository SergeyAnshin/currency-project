package org.good.job.currency.project.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class BelarusBankDto extends GeneralExternalApiDto {

    @JsonAlias({ "kurs_date_time" })
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @JsonAlias({ "USDCARD_in" })
    private double buyUsd;

    @JsonAlias({ "USDCARD_out" })
    private double sellUsd;

    @JsonAlias({ "EURCARD_in" })
    private double buyEur;

    @JsonAlias({ "EURCARD_out" })
    private double sellEur;

    @JsonAlias({ "RUBCARD_in" })
    private double buyRub;

    @JsonAlias({ "RUBCARD_out" })
    private double sellRub;

    @JsonAlias({ "CNYCARD_in" })
    private double buyCny;

    @JsonAlias({ "CNYCARD_out" })
    private double sellCny;

    @JsonAlias({ "USDCARD_EURCARD_in" })
    private double buyConversionUsdEur;

    @JsonAlias({ "USDCARD_EURCARD_out" })
    private double sellConversionUsdEur;

    @JsonAlias({ "USDCARD_RUBCARD_in" })
    private double buyConversionUsdRub;

    @JsonAlias({ "USDCARD_RUBCARD_out" })
    private double sellConversionUsdRub;

    @JsonAlias({ "RUBCARD_EURCARD_out" })
    private double sellConversionRubEur;

    @JsonAlias({ "RUBCARD_EURCARD_in" })
    private double buyConversionRubEur;

    @JsonAlias({ "CNYCARD_USDCARD_in" })
    private double buyConversionCnyUsd;

    @JsonAlias({ "CNYCARD_USDCARD_out" })
    private double sellConversionCnyUsd;

    @JsonAlias({ "CNYCARD_EURCARD_in" })
    private double buyConversionCnyEur;

    @JsonAlias({ "CNYCARD_EURCARD_out" })
    private double sellConversionCnyEur;

    @JsonAlias({ "CNYCARD_RUBCARD_in" })
    private double buyConversionCnyRub;

    @JsonAlias({ "CNYCARD_RUBCARD_out" })
    private double sellConversionCnyRub;

}
