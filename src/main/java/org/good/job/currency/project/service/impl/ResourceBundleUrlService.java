package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.entity.ExternalApiUrlOld;
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
    public String generateRateUrlByExternalApiNameAndCurrencyAndDate(ExternalApiUrlOld param) {
//        var urlMessageCode = param.getExternalApiName()
//                .getExternalApiProperty()
//                .getProperty()
//                .getRateByCurrencyCodeAndDateCode();
//        var currencyCode = param.getCurrency().getCurrencyCode();
//        var date = param.getDate();
//        try {
//            var rateUrl = messageSource.getMessage(urlMessageCode, new Object[]{ currencyCode, date }, Locale.US);
//            if (!rateUrl.isBlank()) {
//                return rateUrl;
//            }
//            throw new NoSuchMessageException(null);
//        } catch (NoSuchMessageException noSuchMessageException) {
//            throw new RateByDateAndCurrencyNotSupportedByApiException();
//        }
        return "";
    }

}
