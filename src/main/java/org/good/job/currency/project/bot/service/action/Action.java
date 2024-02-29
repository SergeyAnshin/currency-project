package org.good.job.currency.project.bot.service.action;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public interface Action {

    SendMessage handle(Update update);

}
