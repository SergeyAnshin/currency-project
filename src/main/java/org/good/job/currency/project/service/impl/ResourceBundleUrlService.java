package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.service.ExternalApiUrlService;
import org.good.job.currency.project.service.exception.RateByDateAndCurrencyNotSupportedByApiException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import java.util.Locale;


@RequiredArgsConstructor

@Service
public class ResourceBundleUrlService implements ExternalApiUrlService {

    private final MessageSource messageSource;

    @Override
    public String generateCurrencyUrlByExternalApiName(ExternalApiUrl param) {
        String urlMessageCode = param.getExternalApiName()
                .getExternalApiProperty()
                .getProperty()
                .getCurrencyByExternalApiName();
        return getUrlWithParamsByMessageCode(urlMessageCode, new Object[]{});
    }

    @Override
    public String generateRateUrlByExternalApiNameAndCurrencyAndDate(ExternalApiUrl param) {
        var urlMessageCode = param.getExternalApiName()
                .getExternalApiProperty()
                .getProperty()
                .getRateByCurrencyCodeAndDateCode();
        var currencyCode = param.getCurrency();
        var date = param.getDate();
        return getUrlWithParamsByMessageCode(urlMessageCode, new Object[]{ currencyCode, date });
    }

    private String getUrlWithParamsByMessageCode(String urlMessageCode, Object[] params) {
        try {
            var url = messageSource.getMessage(urlMessageCode, params, Locale.US);
            if (!url.isBlank()) {
                return url;
            }
            throw new NoSuchMessageException(null);
        } catch (NoSuchMessageException noSuchMessageException) {
            throw new RateByDateAndCurrencyNotSupportedByApiException();
        }
    }

}
