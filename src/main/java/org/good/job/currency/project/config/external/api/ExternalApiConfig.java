package org.good.job.currency.project.config.external.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalApiConfig {
    // TODO найти аннотации для маппинга с ямл файлом
    String currencyExternalApiUrl;
    String rateExternalApiUrl;
    String ratesByPeriodExternalApiUrl;

}
