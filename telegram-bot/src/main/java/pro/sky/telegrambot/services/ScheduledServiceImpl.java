package pro.sky.telegrambot.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import java.util.Collection;

@Service
public class ScheduledServiceImpl implements ScheduledService{

    private final NotificationSenderService notificationSenderService;
    private final NotificationTaskService notificationTaskService;

    public ScheduledServiceImpl(NotificationSenderService notificationSenderService, NotificationTaskService notificationTaskService) {
        this.notificationSenderService = notificationSenderService;
        this.notificationTaskService = notificationTaskService;
    }

    @Override
    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        Collection<NotificationTask> tasks = notificationTaskService.tasksNow();
        for (NotificationTask task : tasks) {
            notificationSenderService.sendNotification(task.getChatId(), "✅Напоминаю: " + task.getText());
        }
    }
}


