package pl.com.dawidm.spring.infrastructure.secutiry.model.user;


import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.com.dawidm.spring.infrastructure.persistence.entity.UserEntity;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.CreateUserResponseDto;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.GetUserDto;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.UserAuthentication;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.UserAuthorization;

import java.util.Arrays;
import java.util.stream.Collectors;


@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserApp {
    Long id;
    String username;
    String email;
    String password;
    String roles;
    boolean enabled;

    public void activate() {
        enabled = true;
    }

    public GetUserDto toGetUserDto() {
        return GetUserDto
                .builder()
                .id(id)
                .username(username)
                .password(password)
                .roles(roles)
                .build();
    }

    public CreateUserResponseDto toCreateUserResponseDto() {
        return CreateUserResponseDto
                .builder()
                .id(id)
                .username(username)
                .build();
    }

    public UserAuthorization toGetUserAuthorization() {
        return UserAuthorization
                .builder()
                .username(username)
                .roles(roles)
                .build();
    }

    public UserAuthentication toGetUserAuthentication() {
        return UserAuthentication
                .builder()
                .username(username)
                .password(password)
                .enabled(enabled)
                .roles(Arrays.stream(roles.split(";"))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()))
                .build();
    }

    public UserEntity toUserEntity() {
        return UserEntity
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
