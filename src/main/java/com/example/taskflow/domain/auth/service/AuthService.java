package com.example.taskflow.domain.auth.service;

import com.example.taskflow.common.security.PasswordEncoder;
import com.example.taskflow.domain.auth.dto.request.SignupRequest;
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


    public SignupResponse signup(SignupRequest signupRequest) {

        if(userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new RuntimeException("중복 아이디 불가");
        }
        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("중복 이메일 불가");
        }

        String encodePassword = passwordEncoder.encode(signupRequest.getPassword());

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(), signupRequest.getName(),
                encodePassword, UserRole.USER);

        User savedUser = userRepository.save(user);
        return SignupResponse.of(savedUser);
    }

}
