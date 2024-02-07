package org.good.job.currency.project.service;

import org.good.job.currency.project.dto.GeneralExternalApiRate;
import org.good.job.currency.project.entity.ExternalApiUrl;


public interface RateChecker {

    boolean isMatchingByCurrencyAndSpecifiedDate(GeneralExternalApiRate rate, ExternalApiUrl externalApiUrl);

    boolean isMatchingByCurrencyAndCurrentDate(GeneralExternalApiRate rate, ExternalApiUrl externalApiUrl);

}
