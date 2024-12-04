package pro.sky.telegrambot;

public class BadMessageException extends RuntimeException {
    public BadMessageException(String message) {
        super(message);
    }
}
