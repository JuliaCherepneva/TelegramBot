package pro.sky.telegrambot.exceptions;

public class BadMessageException extends RuntimeException {
    public BadMessageException(String message) {
        super(message);
    }
}
