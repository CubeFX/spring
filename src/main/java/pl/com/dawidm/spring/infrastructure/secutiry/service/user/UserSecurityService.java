package pl.com.dawidm.spring.infrastructure.secutiry.service.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.UserAuthentication;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        UserAuthentication user = userRepository.getUserByUsername(username).toGetUserAuthentication();
        return new User(user.getUsername(), user.getPassword(), user.getRoles());
    }
}
