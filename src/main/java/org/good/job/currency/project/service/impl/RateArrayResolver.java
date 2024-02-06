package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dto.Foreachable;
import org.good.job.currency.project.dto.GeneralExternalApiRate;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.service.ArrayResolver;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayDeque;


@RequiredArgsConstructor

@Service
public class RateArrayResolver implements ArrayResolver {

    //TODO Протестить, обработка ощибок
    public Object resolve(Object externalApiRateDto, ExternalApiUrl externalApiUrl) {
        if (externalApiRateDto instanceof Foreachable<?> foreachable) {
            var ratesMatchingSpecifiedParameters = new ArrayDeque<>();
            for (var generalExternalApiRate : foreachable.getListDto()) {
                if (generalExternalApiRate instanceof GeneralExternalApiRate rate) {
                    if (isMatchingByCurrencyAndSpecifiedDate(rate, externalApiUrl)) {
                        ratesMatchingSpecifiedParameters.addFirst(rate);
                        break;
                    } else if (isMatchingByCurrencyAndCurrentDate(rate, externalApiUrl)) {
                        ratesMatchingSpecifiedParameters.addLast(rate);
                    }
                }
            }
            if (ratesMatchingSpecifiedParameters.isEmpty()) {
                throw new RuntimeException();
            }
            return ratesMatchingSpecifiedParameters.getFirst();
        }
        throw new RuntimeException();
    }

    private boolean isMatchingByCurrencyAndSpecifiedDate(GeneralExternalApiRate rate, ExternalApiUrl externalApiUrl) {
        return rate.getCurrencyCode().equals(externalApiUrl.getCurrency().getCurrencyCode()) && rate.getDateOfRate()
                .equals(externalApiUrl.getDate());
    }

    private boolean isMatchingByCurrencyAndCurrentDate(GeneralExternalApiRate rate, ExternalApiUrl externalApiUrl) {
        return rate.getCurrencyCode().equals(externalApiUrl.getCurrency().getCurrencyCode()) && rate.getDateOfRate()
                .equals(LocalDate.now());
    }

}
