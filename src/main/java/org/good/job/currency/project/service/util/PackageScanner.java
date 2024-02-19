package org.good.job.currency.project.service.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.good.job.currency.project.entity.enums.ProjectSymbol.BACKSLASH;
import static org.good.job.currency.project.entity.enums.ProjectSymbol.DOT;


public class PackageScanner {

    public static final String ROOT_JAVA_PACKAGE_PATH = "src/main/java/";
    public static final String JAVA_FILE_FORMAT = ".java";
    private static final int JAVA_FILE_FORMAT_LENGTH = 5;

    public static List<? extends Class<?>> getAllJavaClassesInPackageMarkedWithAnnotation(String packageStringPath,
                                                                                          Class<? extends Annotation> annotation) {
        return findAllJavaClassesInPackage(packageStringPath).stream()
                .filter(aClass -> Objects.nonNull(aClass.getAnnotation(annotation)))
                .toList();
    }

    public static List<? extends Class<?>> findAllJavaClassesInPackage(String packageStringPath) {
        Path packagePath = Paths.get(packageStringPath);
        try (var pathStream = Files.walk(packagePath)) {
            return pathStream
                    .filter(Files::isRegularFile)
                    .filter(PackageScanner::isJavaFile)
                    .map(PackageScanner::transformPathToJavaClass)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isJavaFile(Path filePath) {
        return filePath.getFileName().toString().endsWith(JAVA_FILE_FORMAT);
    }

    private static Class<?> transformPathToJavaClass(Path classPath) {
        try {
            String className = convertPathToClassName(classPath);
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String convertPathToClassName(Path classPath) {
        var classPathString = classPath.toString();
        var pathString = classPathString.substring(ROOT_JAVA_PACKAGE_PATH.length(),
                                                   classPathString.length() - JAVA_FILE_FORMAT_LENGTH);
        return replacePathSlashesWithDots(pathString);
    }

    private static String replacePathSlashesWithDots(String pathString) {
        return pathString.replaceAll(BACKSLASH.getSymbol(), DOT.getSymbol());
    }

}
