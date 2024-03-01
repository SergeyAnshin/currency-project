package org.good.job.currency.project.bot.model;


import lombok.Data;
import org.good.job.currency.project.bot.model.enums.UserStep;


@Data
public class UserState {

    private String externalApiName;
    private String currency;

    private UserStep currentStep;

    public UserState(UserStep currentStep) {
        this.currentStep = currentStep;
    }

}
