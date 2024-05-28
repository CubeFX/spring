package pl.com.dawidm.spring.infrastructure.events.publisher;


import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.com.dawidm.spring.infrastructure.events.EventPublisher;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.UserToActivateDto;

@Component
@RequiredArgsConstructor
public class UserToActivatePublisher implements EventPublisher<UserToActivateDto> {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publishEvent(UserToActivateDto userToActivateDto) {
        publisher.publishEvent(userToActivateDto);
    }
}
