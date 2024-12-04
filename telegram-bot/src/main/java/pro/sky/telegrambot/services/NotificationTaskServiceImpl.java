package pro.sky.telegrambot.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pro.sky.telegrambot.BadMessageException;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repositories.NotificationTaskRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Transactional
public class NotificationTaskServiceImpl implements NotificationTaskService{
    private final NotificationTaskRepository notificationTaskRepository;

    public NotificationTaskServiceImpl(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    @Autowired
    private TelegramBot telegramBot;

    Logger logger = LoggerFactory.getLogger(NotificationTaskServiceImpl.class);

    public NotificationTask add(String text) {
        NotificationTask task = new NotificationTask();
        Pattern pattern = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)");
        Matcher matcher = pattern.matcher(text);
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime time = LocalDateTime.parse("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})", dateTime);
        String note = text.substring(17);
        if (matcher.matches()) {
            task.setDateAndTime(time);
            task.setText(note);
            logger.info("message accepted");
            return notificationTaskRepository.save(task);
        }
        throw new BadMessageException("Не правильно составлено сообщение");
    }

    public void sendNotification(Long chatId, String note) {
        SendMessage request = new SendMessage(chatId, note);
        SendResponse response = telegramBot.execute(request);

        logger.info("sent a message {} to the chat {}", note, chatId);

    }

}

