package pro.sky.telegrambot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repositories.NotificationTaskRepository;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Service
@Transactional
public class ScheduledServiceImpl implements ScheduledService{
    private final NotificationTaskService notificationTaskService;
    private final NotificationTaskRepository notificationTaskRepository;

    public ScheduledServiceImpl(NotificationTaskService notificationTaskService, NotificationTaskRepository notificationTaskRepository, TelegramBotUpdatesListener telegramBotUpdatesListener) {
        this.notificationTaskService = notificationTaskService;
        this.notificationTaskRepository = notificationTaskRepository;

    }

    Logger logger = LoggerFactory.getLogger(ScheduledServiceImpl.class);

    @Override
    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        Collection<NotificationTask> tasks = notificationTaskRepository.findByDateAndTime(currentDateTime);

        for (NotificationTask task : tasks) {
            notificationTaskService.sendNotification(task.getChatId(), "✅Напоминаю: " + task.getText());
            logger.info("Sent a notification");
        }
    }
}


