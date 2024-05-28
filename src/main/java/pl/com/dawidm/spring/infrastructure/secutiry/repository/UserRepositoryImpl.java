package pl.com.dawidm.spring.infrastructure.secutiry.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.com.dawidm.spring.infrastructure.persistence.entity.UserEntity;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.UserApp;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.repository.UserRepository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    public final UserSecurityRepository userRepository;


    public UserApp getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserEntity::toUser)
                .orElseThrow(() -> new SecurityException("Cannot find user with ID: " + id));
    }

    public UserApp getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserEntity::toUser)
                .orElseThrow(() -> new SecurityException("Cannot find user with username: " + username));
    }

    @Override
    public UserApp createUser(UserApp user) {
        var userEntity = user.toUserEntity();
        return userRepository.save(userEntity).toUser();
    }

    @Override
    public UserApp updateUser(UserApp user) {
        var userEntity = user.toUserEntity();
        return userRepository.save(userEntity).toUser();
    }

}
