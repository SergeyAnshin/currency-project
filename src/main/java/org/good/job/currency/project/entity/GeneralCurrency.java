package org.good.job.currency.project.entity;

import lombok.Data;


@Data
public class GeneralCurrency implements Comparable<GeneralCurrency> {

    private String buyCurrencyCode;
    private String sellCurrencyCode;

    @Override
    public int compareTo(GeneralCurrency otherGeneralCurrency) {
        return this.sellCurrencyCode.compareToIgnoreCase(otherGeneralCurrency.getSellCurrencyCode());
    }

}
