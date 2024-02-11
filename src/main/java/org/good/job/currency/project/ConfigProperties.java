package org.good.job.currency.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.good.job.currency.project.dao.Bank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "api")
public class ConfigProperties {

    HashMap<String, Bank> banks = new HashMap<>();

}
