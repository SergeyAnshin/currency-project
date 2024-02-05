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
public class AlfaBankRate extends GeneralExternalApiRate {

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

}
