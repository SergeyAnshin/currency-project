package org.good.job.currency.project.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.good.job.currency.project.config.external.api.ExternalApiConfig;
import org.good.job.currency.project.entity.enums.ExternalApiName;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Configuration
@ConfigurationProperties(prefix = "api")
@PropertySource(value = "classpath:external-api.yml", factory = YamlPropertySourceFactory.class)
public class ExternalApiConfigurationProperty {

    HashMap<String, ExternalApiConfig> externalApiConfigMap;

    @PostConstruct
    private void checkPropertiesExistingForExternalApiNames() {
        List<String> missedBankConfigurations = new ArrayList<>();
        for (ExternalApiName value : ExternalApiName.values()) {
            if (externalApiConfigMap.get(value.name()) == null) {
                missedBankConfigurations.add(value.name());
            }
        }
        if (!missedBankConfigurations.isEmpty()) {
            throw new RuntimeException(
                    new StringBuilder("Properties not found for External APIs : ")
                            .append(String.join(", ", missedBankConfigurations))
                            .toString());
        }

    }

}
