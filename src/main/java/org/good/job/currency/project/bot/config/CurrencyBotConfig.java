package org.good.job.currency.project.bot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Data
@Configuration
@PropertySource("classpath:application.yml")
public class CurrencyBotConfig {

    @Value("${bot.name}")
    public String botName;

    @Value("${bot.token}")
    public String botToken;

}
