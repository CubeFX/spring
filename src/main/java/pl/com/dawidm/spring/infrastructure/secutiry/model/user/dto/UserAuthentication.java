package pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthentication {
    private String username;
    private String password;
    private boolean enabled;
    private List<SimpleGrantedAuthority> roles;
}
