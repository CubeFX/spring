package pl.com.dawidm.spring.infrastructure.persistence.exception;

public class CarAppException extends RuntimeException {
    public CarAppException(String message) {
        super(message);
    }
}
