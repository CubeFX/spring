package pl.com.dawidm.spring.infrastructure.events.listener;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.UserApp;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.UserToActivateDto;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.repository.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserToActivateListener {
    private final UserRepository repository;
    //private final JavaMailSender mailSender;

    @Async
    @EventListener
    @Transactional
    public void sendActivationEmail(UserToActivateDto userToActivateDto) {
        var userId = userToActivateDto.getId();
        UserApp userToActivate = repository.getUserById(userId);
        userToActivate.activate();
        repository.updateUser(userToActivate);

//        Wysyłka maila z tokenem aktywacyjnym. Z powodu braku testowej poczty uzytkownika aktywuje bez uzycia tokena
//        Zostawiam funkcjonalnosc zakomentowana jako przyklad uzycia.

//        var token = UUID.randomUUID().toString().replaceAll("\\W", "");
//
//        String email = userToActivate.getEmail();
//        String subject = "Registration activation";
//        String url = "http://localhost:8080/users/activation?token=" + token;
//
//        log.info("Url with activation token: {}", url);
//        log.debug("Send email method is disable");
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(email);
//        simpleMailMessage.setSubject(subject);
//        simpleMailMessage.setText(url);
//
//        mailSender.send(simpleMailMessage);

    }
}
