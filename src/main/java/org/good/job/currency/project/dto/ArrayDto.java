package org.good.job.currency.project.dto;

import java.util.List;


public interface ArrayDto<T extends GeneralExternalApiDto> {

    List<T> getListDto();

}
