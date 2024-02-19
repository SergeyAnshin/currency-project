package org.good.job.currency.project.dao;

import org.good.job.currency.project.entity.GeneralCurrency;
import org.good.job.currency.project.entity.enums.ExternalApiName;

import java.util.List;


public interface CurrencyDao {

    List<GeneralCurrency> findByExternalApiName(ExternalApiName externalApiName);

}
