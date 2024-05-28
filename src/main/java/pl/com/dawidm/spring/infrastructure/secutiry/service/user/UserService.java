package pl.com.dawidm.spring.infrastructure.secutiry.service.user;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.com.dawidm.spring.configuration.validator.Validator;
import pl.com.dawidm.spring.infrastructure.events.EventPublisher;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.CreateUserDto;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.CreateUserResponseDto;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.UserToActivateDto;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.validator.CreateUserDtoValidator;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventPublisher<UserToActivateDto> publisher;

    @Transactional
    public CreateUserResponseDto createUser(CreateUserDto createUserDto) {
        Validator.validate(new CreateUserDtoValidator(), createUserDto);
        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        var user = userRepository.createUser(createUserDto.toUser()).toCreateUserResponseDto();
        publisher.publishEvent(UserToActivateDto.builder().id(user.getId()).build());
        return user;
    }

}
