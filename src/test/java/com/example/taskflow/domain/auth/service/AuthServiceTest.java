package com.example.taskflow.domain.auth.service;

import com.example.taskflow.common.enums.ErrorCode;
import com.example.taskflow.common.exception.TaskFlowException;
import com.example.taskflow.common.security.PasswordEncoder;
import com.example.taskflow.domain.auth.dto.request.SignupRequest;
import com.example.taskflow.domain.auth.dto.response.SignupResponse;
import com.example.taskflow.domain.user.entity.User;
import com.example.taskflow.domain.user.enums.UserRole;
import com.example.taskflow.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void signUp_회원가입_성공() {
        //given
        SignupRequest signupRequest = new SignupRequest("username", "email", "password", "name");
        User mockUser = new User("username", "email", "name", "password", UserRole.USER);

        when(userRepository.existsByUsername("username")).thenReturn(false);
        when(userRepository.existsByEmail("email")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("password");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        //when
        SignupResponse result = authService.signup(signupRequest);

        //then
        assertThat(result.getUsername()).isEqualTo("username");
        assertThat(result.getEmail()).isEqualTo("email");
        assertThat(result.getRole()).isEqualTo(UserRole.USER);
        assertThat(result.getName()).isEqualTo("name");

        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode("password");
    }

    @Test
    void signUp_회원가입_실패_중복_아이디(){
        //given
        SignupRequest signupRequest = new SignupRequest("username", "email", "password", "name");

        when(userRepository.existsByUsername("username")).thenReturn(true);
        //when
        TaskFlowException e = assertThrows(TaskFlowException.class, () -> {
            authService.signup(signupRequest);
                });
        //then
        assertThat(e.getErrorCode()).isEqualTo(ErrorCode.USER_NAME_EXISTS);
    }

    @Test
    void signUp_회원가입_실패_중복_이메일(){
        //given
        SignupRequest signupRequest = new SignupRequest("username", "email", "password", "name");
        when(userRepository.existsByEmail("email")).thenReturn(true);
        //when
        TaskFlowException e = assertThrows(TaskFlowException.class, () -> {
            authService.signup(signupRequest);
        });
        //then
        assertThat(e.getErrorCode()).isEqualTo(ErrorCode.USER_EMAIL_EXISTS);
    }


}