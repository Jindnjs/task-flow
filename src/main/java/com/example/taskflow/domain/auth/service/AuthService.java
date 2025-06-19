package com.example.taskflow.domain.auth.service;

import com.example.taskflow.common.enums.ErrorCode;
import com.example.taskflow.common.exception.TaskFlowException;
import com.example.taskflow.common.utils.JwtUtil;
import com.example.taskflow.common.utils.PasswordEncoder;
import com.example.taskflow.domain.auth.dto.request.SigninRequest;
import com.example.taskflow.domain.auth.dto.request.SignupRequest;
import com.example.taskflow.domain.auth.dto.response.SigninResopnse;
import com.example.taskflow.domain.auth.dto.response.SignupResponse;
import com.example.taskflow.domain.user.entity.User;
import com.example.taskflow.domain.user.enums.UserRole;
import com.example.taskflow.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public SignupResponse signup(SignupRequest signupRequest) {

        if(userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new TaskFlowException(ErrorCode.USER_NAME_EXISTS);
        }
        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new TaskFlowException(ErrorCode.USER_EMAIL_EXISTS);
        }

        String encodePassword = passwordEncoder.encode(signupRequest.getPassword());

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(), signupRequest.getName(),
                encodePassword, UserRole.USER);

        User savedUser = userRepository.save(user);
        return SignupResponse.of(savedUser);
    }

    public SigninResopnse signin(SigninRequest signinRequest) {
        User user = userRepository.findByUsername(signinRequest.getUsername())
                .orElseThrow(() -> new TaskFlowException(ErrorCode.USER_LOGIN_UNAUTHORIZED));

        if(!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
            throw new TaskFlowException(ErrorCode.USER_LOGIN_UNAUTHORIZED);
        }

        String token = jwtUtil.createToken(user.getId(), user.getRole());

        return new SigninResopnse(token);
    }
}
