package pl.com.dawidm.spring.infrastructure.secutiry.model.user.repository;

import pl.com.dawidm.spring.infrastructure.secutiry.model.user.UserApp;

public interface UserRepository {

    UserApp getUserById(Long id);

    UserApp getUserByUsername(String username);

    UserApp createUser(UserApp user);

    UserApp updateUser(UserApp user);
}
