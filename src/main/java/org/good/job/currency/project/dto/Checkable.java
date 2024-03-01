package org.good.job.currency.project.dto;

import java.time.LocalDate;


public interface Checkable {

    int getSellCurrencyCodeId();

    String getSellCurrencyCode();

    String getBuyCurrencyCode();

    LocalDate getDateOfRate();

}
