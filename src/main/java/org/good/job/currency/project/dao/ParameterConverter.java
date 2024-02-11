package org.good.job.currency.project.dao;

import java.util.HashMap;


public interface ParameterConverter {
    String convert(ExternalApiUrl externalApiUrl, HashMap<String, String> externalApiParameters);
}
