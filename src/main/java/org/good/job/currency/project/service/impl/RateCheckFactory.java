package org.good.job.currency.project.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dto.GeneralExternalApiRate;
import org.good.job.currency.project.service.RateChecker;
import org.good.job.currency.project.service.annotations.AssignedClass;
import org.good.job.currency.project.service.exception.AssignedClassMissingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;


@RequiredArgsConstructor

@Service
public class RateCheckFactory {

    private final List<RateChecker> rateCheckers;
    private final Map<String, RateChecker> rateCheckerByExternalApiRateSimpleName;

    @PostConstruct
    private void init() {
        for (RateChecker rateChecker : rateCheckers) {
            var assignedClassAnnotation = rateChecker.getClass().getAnnotation(AssignedClass.class);
            if (nonNull(assignedClassAnnotation)) {
                Class<?> externalApiRateClass = assignedClassAnnotation.value();
                String externalApiRateSimpleName = externalApiRateClass.getSimpleName();
                rateCheckerByExternalApiRateSimpleName.put(externalApiRateSimpleName, rateChecker);
            } else {
                throw new AssignedClassMissingException();
            }
        }
    }

    public RateChecker getRateCheckerByRateDtoClass(Class<? extends GeneralExternalApiRate> externalApiRate) {
        String externalApiRateSimpleName = externalApiRate.getSimpleName();
        return rateCheckerByExternalApiRateSimpleName.get(externalApiRateSimpleName);
    }

}
