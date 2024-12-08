package pro.sky.telegrambot.services;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import pro.sky.telegrambot.model.NotificationTask;

public interface NotificationTaskService {
   NotificationTask add (Message message);
   void sendNotification(Long chatId, String note);
}
