package pl.com.dawidm.spring.infrastructure.secutiry.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.AuthRequest;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.CreateUserDto;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.CreateUserResponseDto;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.Token;
import pl.com.dawidm.spring.infrastructure.secutiry.service.jwt.JwtService;
import pl.com.dawidm.spring.infrastructure.secutiry.service.user.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/security/")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponseDto> registerUser(@RequestBody CreateUserDto user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/generateToken")
    public ResponseEntity<Token> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(Token.builder()
                    .token(jwtService.generateToken(authRequest.getUsername()))
                    .build(), HttpStatus.CREATED);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

}
