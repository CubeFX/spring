package pl.com.dawidm.spring.infrastructure.secutiry.repository;


import org.springframework.data.repository.CrudRepository;
import pl.com.dawidm.spring.infrastructure.persistence.entity.UserEntity;

import java.util.Optional;

public interface UserSecurityRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String userName);
}
