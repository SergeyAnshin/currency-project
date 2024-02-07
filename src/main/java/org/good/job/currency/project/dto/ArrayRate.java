package org.good.job.currency.project.dto;

import java.util.List;


public interface ArrayRate<T extends GeneralExternalApiRate> {

    List<T> getListDto();

}
