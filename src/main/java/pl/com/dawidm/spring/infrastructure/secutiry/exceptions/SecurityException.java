package pl.com.dawidm.spring.infrastructure.secutiry.exceptions;

public class SecurityException extends RuntimeException {
    public SecurityException(String message) {
        super(message);
    }
}
