package pl.com.dawidm.spring.infrastructure.events.exception;

public class EventsException extends RuntimeException {
    public EventsException(String message) {
        super(message);
    }
}
