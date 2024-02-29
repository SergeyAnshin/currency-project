package org.good.job.currency.project.bot.dao;

import lombok.Data;
import org.good.job.currency.project.bot.model.UserState;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
@Data
public class UserDao {

    private final HashMap<String, UserState> userStateHashMap = new HashMap<>();

}
