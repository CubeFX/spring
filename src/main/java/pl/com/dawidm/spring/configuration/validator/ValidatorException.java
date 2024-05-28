package pl.com.dawidm.spring.configuration.validator;

public class ValidatorException extends RuntimeException {
    public ValidatorException(String message) {
        super(message);
    }
}
