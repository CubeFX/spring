package pl.com.dawidm.spring.infrastructure.secutiry.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.com.dawidm.spring.configuration.validator.ValidatorException;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.AuthRequest;
import pl.com.dawidm.spring.infrastructure.secutiry.model.user.dto.CreateUserDto;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.com.dawidm.spring.infrastructure.utils.ToJsonMapper.toJson;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRegisterUserWhenCreateUserDtoIsCorrect() throws Exception {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .roles("ADMIN")
                .email("user@email.com")
                .username("UserAdmin")
                .password("UserAdmin!88")
                .passwordConfirmation("UserAdmin!88")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/security/register")
                        .content(toJson(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(createUserDto.getUsername()))
                .andReturn();
    }

    @Test
    void shouldNotRegisterUserWhenCreateUserDtoIsInValid() throws Exception {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .roles("ADMIN")
                .email("user@email.com")
                .username("UserAdmin")
                .password("useradmin")
                .passwordConfirmation("useradmin")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/security/register")
                        .content(toJson(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(result -> assertInstanceOf(ValidatorException.class, result.getResolvedException()))
                .andReturn();
    }

    @Test
    void shouldNotAddNewUserWhenExistsInDatabase() throws Exception {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .roles("ADMIN")
                .email("email@email.com")
                .username("UserAdmin")
                .password("UserAdmin!88")
                .passwordConfirmation("UserAdmin!88")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/security/register")
                        .content(toJson(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        CreateUserDto createUserDto2 = CreateUserDto.builder()
                .roles("ADMIN")
                .email("email@email.com")
                .username("UserAdmin")
                .password("UserAdmin!88")
                .passwordConfirmation("UserAdmin!88")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/security/register")
                        .content(toJson(createUserDto2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(result -> assertInstanceOf(DataIntegrityViolationException.class, result.getResolvedException()))
                .andReturn();
    }

    @Test
    void shouldReturnTokenWhenUserExists() throws Exception {
        CreateUserDto createUserDto1 = CreateUserDto.builder()
                .roles("ADMIN1")
                .email("useradmin@email.com")
                .username("UserAdmin1")
                .password("UserAdmin1!88")
                .passwordConfirmation("UserAdmin1!88")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/security/register")
                        .content(toJson(createUserDto1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        AuthRequest authRequest = AuthRequest.builder()
                .username("UserAdmin1")
                .password("UserAdmin1!88")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/security/generateToken")
                        .content(toJson(authRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("token")))
                .andReturn();
    }

    @Test
    void shouldReturnExceptionInstedOfTokenWhenUserNotExists() throws Exception {
        AuthRequest authRequest = AuthRequest.builder()
                .username("User")
                .password("Password")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/security/generateToken")
                        .content(toJson(authRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(result -> assertInstanceOf(InternalAuthenticationServiceException.class, result.getResolvedException()))
                .andReturn();
    }
}