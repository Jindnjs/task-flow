package com.example.taskflow.domain.dashboard.service;

import com.example.taskflow.domain.dashboard.dto.DashboardResponse;
import com.example.taskflow.domain.dashboard.repository.QTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final QTaskRepository qTaskRepository;

    public DashboardResponse stats(Long userId) {

       return qTaskRepository.stats(userId);
    }
}
