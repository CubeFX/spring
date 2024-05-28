package pl.com.dawidm.spring.infrastructure.secutiry.exceptions;

public class UsersServiceException extends RuntimeException {
    public UsersServiceException(String message) {
        super(message);
    }
}
