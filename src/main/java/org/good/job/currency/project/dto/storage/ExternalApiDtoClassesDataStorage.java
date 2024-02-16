package org.good.job.currency.project.dto.storage;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dto.storage.annotation.AssignedExternalApiDto;
import org.good.job.currency.project.dto.storage.impl.ExternalApiAnnotatedDtoClassesDataCreator;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.springframework.stereotype.Service;

import java.util.Map;


@RequiredArgsConstructor

@Service
public class ExternalApiDtoClassesDataStorage {

    private final ExternalApiDtoClassesFinder dtoClassesFinder;
    private final ExternalApiAnnotatedDtoClassesDataCreator classesDataCreator;
    private final Map<String, ExternalApiDtoClassesData> dtoClassesDataByExternalApiName;

    @PostConstruct
    public void init() {
        var dtoClasses = dtoClassesFinder.getAllExternalApiDtosMarkedByAnnotation(AssignedExternalApiDto.class);
        for (Class<?> dtoClass : dtoClasses) {
            var assignedExternalApiDtoAnnotation = dtoClass.getAnnotation(AssignedExternalApiDto.class);
            var dtoClassesData = classesDataCreator.create(assignedExternalApiDtoAnnotation);
            var externalApiName = assignedExternalApiDtoAnnotation.externalApi().name();
            dtoClassesDataByExternalApiName.put(externalApiName, dtoClassesData);
        }
    }

    public ExternalApiDtoClassesData getByExternalApiName(ExternalApiName externalApiName) {
        var name = externalApiName.name();
        return dtoClassesDataByExternalApiName.get(name);
    }

}
