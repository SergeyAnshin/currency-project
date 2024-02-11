package org.good.job.currency.project.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bank {

    private ExternalApiUrl currencyExternalApiUrl;
    private ExternalApiUrl rateExternalApiUrl;

}
