package org.good.job.currency.project.dto;

import java.time.LocalDate;
import java.util.Currency;


public interface Checkable {

    String getSellCurrencyCode();

    String getBuyCurrencyCode();

    LocalDate getDateOfRate();

    Currency getLocalCurrency();

}
