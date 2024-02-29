package org.good.job.currency.project.bot.service.action;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.bot.model.enums.UserStep;
import org.springframework.stereotype.Component;

import java.util.Map;


@RequiredArgsConstructor
@Component
public class ActionFactory {
    private final Map<String, Action> stringActionMap;

    public Action getAction(UserStep userStep){
        return  stringActionMap.get(userStep.getActionBeanName());
    }
}
