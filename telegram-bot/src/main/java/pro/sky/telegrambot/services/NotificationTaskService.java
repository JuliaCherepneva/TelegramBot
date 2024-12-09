package pro.sky.telegrambot.services;

import com.pengrad.telegrambot.model.Update;
import pro.sky.telegrambot.model.NotificationTask;

public interface NotificationTaskService {
   NotificationTask add (Update update);
   void sendNotification(Long chatId, String note);
}
