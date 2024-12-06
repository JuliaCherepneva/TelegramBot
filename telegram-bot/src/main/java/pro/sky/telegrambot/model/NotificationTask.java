package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column (name = "chatid")
    private long chatId;

    private String text;

    private LocalDateTime dateAndTime;

    public NotificationTask () {
    }

    public NotificationTask(long chatId, String text, LocalDateTime dateAndTime) {
        this.chatId = chatId;
        this.text = text;
        this.dateAndTime = dateAndTime;
    }

    public long getId() {
        return id;
    }

    public long getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", text='" + text + '\'' +
                ", dateAndTime=" + dateAndTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id && chatId == that.chatId && Objects.equals(text, that.text) && Objects.equals(dateAndTime, that.dateAndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, text, dateAndTime);
    }
}
