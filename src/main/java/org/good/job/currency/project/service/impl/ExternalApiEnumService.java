package org.good.job.currency.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.good.job.currency.project.service.ExternalApiService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor

@Service
public class ExternalApiEnumService implements ExternalApiService {

    public static final String ENUM_WORDS_DELIMITER = "_";
    public static final String WHITESPACE_CHARACTER = " ";

    @Override
    public List<String> getAvailableExternalApis() {
        return Arrays.stream(ExternalApiName.values())
                .map(Enum::name)
                .map(String::toLowerCase)
                .map(externalApiName -> externalApiName.replaceAll(ENUM_WORDS_DELIMITER, WHITESPACE_CHARACTER))
                .map(StringUtils::capitalize)
                .collect(Collectors.toList());
    }

}
