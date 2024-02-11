package org.good.job.currency.project.dao.impl;


import org.apache.commons.text.StringSubstitutor;
import org.good.job.currency.project.dao.ExternalApiUrl;
import org.good.job.currency.project.dao.ParameterConverter;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ParameterConverterImpl implements ParameterConverter {

    @Override
    public String convert(ExternalApiUrl externalApiUrl, HashMap<String, String> externalApiParameters) {
        if (externalApiParameters.isEmpty()) return externalApiUrl.getName();
        for (String parameter : externalApiUrl.getParameters()) {
            if (externalApiParameters.get(parameter) == null) {
                throw new RuntimeException("Not found '" + parameter +
                                                   "' for URL '" + externalApiUrl.getName() +
                                                   "' in external API parameters");
            }
        }
        return StringSubstitutor.replace(externalApiUrl.getName(), externalApiParameters);
    }

}
