package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringSubstitutor;
import org.good.job.currency.project.entity.UserRequestParametersData;
import org.good.job.currency.project.service.ExternalApiUrlService;
import org.good.job.currency.project.service.annotation.UrlParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.good.job.currency.project.entity.enums.ExternalApiName.NATIONAL_BANK;
import static org.good.job.currency.project.entity.enums.ExternalApiName.getExternalCurrencyId;


@RequiredArgsConstructor

@Service
public class ExternalApiUrlServiceImpl implements ExternalApiUrlService {

    public static final String GETTER_PREFIX = "get";

    @Override
    public String substituteParametersInUrl(UserRequestParametersData userRequestParameters, String externalApiUrl) {
        var parametersMap = getParametersMapForUrlConfig(userRequestParameters);
        if (parametersMap.isEmpty()) return externalApiUrl;
        return StringSubstitutor.replace(externalApiUrl, parametersMap, "{{", "}}");
    }

    private HashMap<String, String> getParametersMapForUrlConfig(UserRequestParametersData userRequestParameters) {
        var urlParametersClass = userRequestParameters.getClass();
        var fields = urlParametersClass.getDeclaredFields();
        HashMap<String, String> urlParametersWithValues = new HashMap<>();
        for (var field : fields) {
            var annotationWithUrlParameterName = field.getAnnotation(UrlParameter.class);
            if (!nonNull(annotationWithUrlParameterName)) continue;
            var urlParameterName = annotationWithUrlParameterName.name();
            var getterMethodName = GETTER_PREFIX + StringUtils.capitalize(field.getName());
            var method = getMethodByName(urlParametersClass, getterMethodName);
            var parameterValue = extractParameterValue(method, userRequestParameters).map(
                    targetCurrencyCode -> replaceCurrencyCodeWithCurrencyIdIfExternalApiNotSupport(
                            userRequestParameters, targetCurrencyCode));
            parameterValue.ifPresent(value -> urlParametersWithValues.put(urlParameterName, value));
        }
        return urlParametersWithValues;
    }

    private String replaceCurrencyCodeWithCurrencyIdIfExternalApiNotSupport(UserRequestParametersData userRequestParameters,
                                                                            String targetCurrencyCode) {
        if (Objects.equals(userRequestParameters.getTargetCurrencyCode(), targetCurrencyCode)) {
            if (userRequestParameters.getExternalApiName().equals(NATIONAL_BANK)) {
                return getExternalCurrencyId(NATIONAL_BANK, targetCurrencyCode).toString();
            }
        }
        return targetCurrencyCode;
    }

    private Method getMethodByName(Class<? extends UserRequestParametersData> paramClass, String getterMethodName) {
        try {
            return paramClass.getDeclaredMethod(getterMethodName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<String> extractParameterValue(Method method, UserRequestParametersData userRequestParametersData) {
        try {
            method.setAccessible(true);
            var invoke = method.invoke(userRequestParametersData);
            return nonNull(invoke) ? Optional.of(invoke.toString()) : Optional.empty();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


}
