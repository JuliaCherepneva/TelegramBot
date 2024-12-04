package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;


import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.List;




@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
        String text = update.message().text();
        if (text.equals("/start")) {
            SendResponse response = telegramBot.execute(new SendMessage(update.message().chat().id(),
                    "Добро пожаловать! Я могу напомнить вам выполнить любую задачу! Вы можете написать дату и время напоминания в формате: ДД.ММ.ГГГГ ЧЧ:ММ, и текст напоминания в одном сообщении. А далее дело за мной ;) В указанную дату и время вы получите от меня уведомление. Желаю удачи!"));
        }
        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}






