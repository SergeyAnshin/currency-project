package org.good.job.currency.project.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExternalApiUrl {

    private String name;
    private List<String> parameters;

}
