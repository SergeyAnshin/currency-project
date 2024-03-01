package org.good.job.currency.project.dto.storage;

import java.lang.annotation.Annotation;
import java.util.List;


public interface ExternalApiDtoClassesFinder {

    List<? extends Class<?>> getAllExternalApiDtosMarkedByAnnotation(Class<? extends Annotation> annotation);

}
