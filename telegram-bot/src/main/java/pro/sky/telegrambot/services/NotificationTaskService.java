package pro.sky.telegrambot.services;

import pro.sky.telegrambot.model.NotificationTask;

public interface NotificationTaskService {
   NotificationTask add (String message);
   void sendNotification(Long chatId, String note);
}
