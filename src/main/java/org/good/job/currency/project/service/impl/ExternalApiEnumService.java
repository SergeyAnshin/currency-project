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

    @Override
    public List<String> getAvailableExternalApis() {
        return Arrays.stream(ExternalApiName.values())
                .map(Enum::name)
                .map(String::toLowerCase)
                .map(externalApiName -> externalApiName.replaceAll("_", " "))
                .map(StringUtils::capitalize)
                .collect(Collectors.toList());
    }

}
