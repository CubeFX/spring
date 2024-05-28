package pl.com.dawidm.spring.infrastructure.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.com.dawidm.spring.infrastructure.persistence.entity.base.BaseEntity;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.UserApp;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private String roles;

    public UserApp toUser() {
        return UserApp
                .builder()
                .id(id)
                .username(username)
                .email(email)
                .password(password)
                .roles(roles)
                .enabled(enabled)
                .build();
    }
}
