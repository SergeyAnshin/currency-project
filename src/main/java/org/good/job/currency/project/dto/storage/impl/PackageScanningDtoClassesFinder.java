package org.good.job.currency.project.dto.storage.impl;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.dto.storage.ExternalApiDtoClassesFinder;
import org.good.job.currency.project.service.util.PackageScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.List;


@RequiredArgsConstructor

@Service
public class PackageScanningDtoClassesFinder implements ExternalApiDtoClassesFinder {

    @Value("${app.root-dto-package-path}")
    private String rootDtoPackagePath;

    @Override
    public List<? extends Class<?>> getAllExternalApiDtosMarkedByAnnotation(Class<? extends Annotation> annotation) {
        return PackageScanner.getAllJavaClassesInPackageMarkedWithAnnotation(rootDtoPackagePath, annotation);
    }

}
