package pro.sky.telegrambot.services;

import org.springframework.scheduling.annotation.Scheduled;
import pro.sky.telegrambot.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.Collection;

public interface ScheduledService {

    void run();

}
