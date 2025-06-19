package com.example.taskflow.domain.dashboard.repository;

import com.example.taskflow.domain.dashboard.dto.DashboardResponse;
import com.example.taskflow.domain.dashboard.dto.QDashboardResponse;
import com.example.taskflow.domain.task.entity.QTask;
import com.example.taskflow.domain.task.enums.Status;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class QTaskRepository {
    private final JPAQueryFactory queryFactory;

    public DashboardResponse stats(Long userId) {
        QTask qTask = QTask.task;

        LocalDateTime now = LocalDateTime.now();
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime startOfTomorrow = today.plusDays(1).atStartOfDay();

        return queryFactory
                .select(
                    new QDashboardResponse(
                        qTask.count(),
                        qTask.status.when(Status.DONE).then(1).otherwise(0).sum(),
                        qTask.status.when(Status.IN_PROGRESS).then(1).otherwise(0).sum(),
                        qTask.status.when(Status.TODO).then(1).otherwise(0).sum(),
                        new CaseBuilder().when(qTask.status.in(Status.TODO, Status.IN_PROGRESS)
                                .and(qTask.dueDate.before(now))).then(1).otherwise(0).sum(),
                        new CaseBuilder().when(qTask.status.eq(Status.TODO).and(qTask.dueDate.goe(startOfDay))
                                .and(qTask.dueDate.lt(startOfTomorrow))).then(1).otherwise(0).sum()
                    )
                )
                .from(qTask)
                .where(qTask.isDeleted.isFalse())
                .fetchOne();
    }
}
