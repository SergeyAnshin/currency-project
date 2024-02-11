package org.good.job.currency.project.service.impl;

import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class ExternalApiEnumServiceTest {

    // "National bank", "1 bank", "1 alfa bank"
    public static final String CORRECT_EXTERNAL_API_NAME_REGEX = "^([A-Z0-9]{1}[a-z0-9]+){1}(\\s[a-z0-9]+)*$";
    @Autowired
    private ExternalApiEnumService apiService;

    @Test
    void availableExternalApisListContainsAllApisFromExternalApiNameEnum() {
        var availableExternalApis = apiService.getAvailableExternalApis();
        boolean containsAll = Arrays.stream(ExternalApiName.values())
                .map(Enum::name)
                .map(e -> e.replaceAll("_", " "))
                .map(String::toLowerCase)
                .map(StringUtils::capitalize)
                .allMatch(availableExternalApis::contains);
        assertTrue(containsAll);
    }

    @Test
    void availableExternalApisFormatIsCorrect() {
        var isFormatAllExternalApisCorrect = apiService.getAvailableExternalApis()
                .stream()
                .allMatch(externalApiName -> Pattern.matches(CORRECT_EXTERNAL_API_NAME_REGEX, externalApiName));
        assertTrue(isFormatAllExternalApisCorrect);
    }

}