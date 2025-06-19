package com.example.taskflow.domain.user.service;

import com.example.taskflow.common.enums.ErrorCode;
import com.example.taskflow.common.exception.TaskFlowException;
import com.example.taskflow.domain.user.dto.ProfileResponse;
import com.example.taskflow.domain.user.entity.User;
import com.example.taskflow.domain.user.repository.QUserRepository;
import com.example.taskflow.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final QUserRepository qUserRepository;

    public ProfileResponse profile(Long userId) {
        User user = getExistUserById(userId);
        return ProfileResponse.toDto(user);
    }

    public User getExistUserById(Long userId) {
        return userRepository.findByIdAndIsDeleted(userId, false)
                .orElseThrow(()-> new TaskFlowException(ErrorCode.USER_NOT_FOUND));
    }

    public List<ProfileResponse> getProfiles() {
        return qUserRepository.findAllByIsDeletedFalse();
    }
}
