package pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.UserApp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    private String username;
    private String email;
    private String password;
    private String passwordConfirmation;
    private String roles;

    public UserApp toUser() {
        return UserApp
                .builder()
                .username(username)
                .email(email)
                .password(password)
                .roles(roles)
                .enabled(false)
                .build();
    }
}
