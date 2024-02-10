package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.entity.ExternalApiUrl;
import org.good.job.currency.project.service.ExternalApiUrlParameterConverter;
import org.good.job.currency.project.service.annotations.UrlParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.nonNull;


@Service
public class ExternalApiUrlParameterConverterImpl implements ExternalApiUrlParameterConverter {

    public static final String GETTER_PREFIX = "get";

    @Override
    public Map<String, String> convert(ExternalApiUrl externalApiUrlParameters) {
        var urlParametersClass = externalApiUrlParameters.getClass();
        var fields = urlParametersClass.getDeclaredFields();
        HashMap<String, String> urlParametersWithValues = new HashMap<>();
        for (var field : fields) {
            var annotationWithUrlParameterName = field.getAnnotation(UrlParameter.class);
            if (nonNull(annotationWithUrlParameterName)) {
                var urlParameterName = annotationWithUrlParameterName.name();
                var getterMethodName = GETTER_PREFIX + StringUtils.capitalize(field.getName());
                var method = getMethodByName(urlParametersClass, getterMethodName);
                var parameterValue = extractParameterValue(method, externalApiUrlParameters);
                parameterValue.ifPresent(value -> urlParametersWithValues.put(urlParameterName, value));
            }
        }
        return urlParametersWithValues;
    }

    private Method getMethodByName(Class<? extends ExternalApiUrl> paramClass, String getterMethodName) {
        try {
            return paramClass.getDeclaredMethod(getterMethodName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<String> extractParameterValue(Method method, ExternalApiUrl externalApiUrlParameters) {
        try {
            method.setAccessible(true);
            var invoke = method.invoke(externalApiUrlParameters);
            return nonNull(invoke) ? Optional.of(invoke.toString()) : Optional.empty();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
