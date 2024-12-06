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
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repositories.NotificationTaskRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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

    public NotificationTask add(String text) throws BadMessageException {
        try {
            NotificationTask task = new NotificationTask();
            Pattern pattern = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
                logger.info("String matches expected pattern");

                // Извлечение даты и времени
                String dateTimeString = matcher.group(1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.ENGLISH);
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                task.setDateAndTime(dateTime);
                logger.info("Parsed date and time: " + dateTime);

                //Извлечение уведомления
                String note = matcher.group(3); //Получаем текст после даты и времени
                task.setText(note);
                logger.info("Parsed note: " + note);

                //Отправка сообщения через Telegram
                telegramBot.execute(new SendMessage(task.getChatId(), "Ваше сообщение принято"));
                logger.info("sent a message {} to the chat {}", note, task.getChatId());
                logger.info("Message accepted and saved");

                return notificationTaskRepository.save(task);
            }
        } catch (BadMessageException e) {
            logger.warn("Message does not match expected pattern");
            throw new BadMessageException("Не правильно составлено сообщение");
        }
        return new NotificationTask();

    }


    public void sendNotification(Long chatId, String note) {
       //Надо получить chatId

        SendMessage request = new SendMessage(chatId, note);
        SendResponse response = telegramBot.execute(request);

        logger.info("sent a message {} to the chat {}", note, chatId);

    }

}

