package pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.validator;


import pl.com.dawidm.spring.configuration.validator.Validator;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.CreateUserDto;

import java.util.HashMap;
import java.util.Map;

public class CreateUserDtoValidator implements Validator<CreateUserDto> {

    private final Map<String, String> validateErrors = new HashMap<String, String>();

    @Override
    public Map<String, String> validate(CreateUserDto createUserDto) {
        checkUsername(createUserDto.getUsername());
        checkEmail(createUserDto.getEmail());
        checkRole(createUserDto.getRoles());
        checkPassword(createUserDto.getPassword());
        checkPasswordConfirmation(createUserDto.getPassword(), createUserDto.getPasswordConfirmation());

        return this.validateErrors;
    }

    private void checkUsername(String username) {
        if (username == null || username.isEmpty()) {
            this.validateErrors.put("username", "Username is required");
        } else if (!username.trim().matches("^[a-zA-Z0-9_-]*$")) {
            this.validateErrors.put("username", "Username contains invalid characters");
        }
    }

    private void checkEmail(String email) {
        if (email == null || email.isEmpty()) {
            this.validateErrors.put("email", "Email is required");
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{3,30}$")) {
            this.validateErrors.put("email", "Email contains invalid characters");
        }
    }

    private void checkRole(String roles) {
        if (roles == null || roles.isEmpty()) {
            this.validateErrors.put("roles", "Roles is required");
        } else if (!roles.matches("^[\\w-;]+$")) {
            this.validateErrors.put("roles", "Roles contains invalid characters");
        }
    }

    private void checkPassword(String password) {
        if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")) {
            this.validateErrors.put("password", "Password is required - Minimum eight characters, at least one uppercase letter, one lowercase letter");
        }
    }

    private void checkPasswordConfirmation(String password, String confirmation) {
        if (!password.equals(confirmation) || confirmation.isEmpty()) {
            this.validateErrors.put("passwordConfirmation", "Passwords do not match");
        }
    }
}
