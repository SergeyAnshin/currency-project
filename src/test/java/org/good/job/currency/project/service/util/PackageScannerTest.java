package org.good.job.currency.project.service.util;

import org.good.job.currency.project.dto.storage.annotation.AssignedExternalApiDto;
import org.good.job.currency.project.service.annotation.AssignedClass;
import org.good.job.currency.project.service.util.testfile.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO ROOT_JAVA_PACKAGE_PATH and TEST_JAVA_PACKAGE_PATH

class PackageScannerTest {

    @Test
    void returnAllAnnotatedJavaClassesInTestFilesPackage() {
        var testFilesPackagePath = "src/test/java/org/good/job/currency/project/service/util/testfile";
        List<Object> objects = List.of(AnnotatedBankDto.class, AnnotatedCryptoBankDto.class);
        List<Object> objects1 = List.of(AnnotatedService.class, AnnotatedServiceTwo.class);
        Assertions.assertAll(() -> {
            assertEquals(objects, PackageScanner.getAllJavaClassesInPackageMarkedWithAnnotation(testFilesPackagePath,
                                                                                                AssignedExternalApiDto.class));
            assertEquals(objects1, PackageScanner.getAllJavaClassesInPackageMarkedWithAnnotation(testFilesPackagePath,
                                                                                                 AssignedClass.class));
        });
    }

    @Test
    void returnAllJavaClassesInTestFilesPackage() {
        var testFilesPackagePath = "src/test/java/org/good/job/currency/project/service/util/testfile";
        List<Class<?>> classes = List.of(AnnotatedBankDto.class, AnnotatedCryptoBankDto.class, AnnotatedService.class,
                                         AnnotatedServiceTwo.class, NotAnnotatedBankDto.class);
        assertEquals(classes, PackageScanner.findAllJavaClassesInPackage(testFilesPackagePath));
    }

}