package org.good.job.currency.project.dto.storage;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ExternalApiDtoClassesData {

    private Class<?> rateDto;
    private Class<?> currencyDto;

}
