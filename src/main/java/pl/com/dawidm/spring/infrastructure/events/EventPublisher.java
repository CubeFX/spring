package pl.com.dawidm.spring.infrastructure.events;

public interface EventPublisher<T> {
    void publishEvent(T t);
}
