package com.example.taskflow.domain.user.repository;

import com.example.taskflow.domain.user.dto.ProfileResponse;
import com.example.taskflow.domain.user.dto.QProfileResponse;
import com.example.taskflow.domain.user.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QUserRepository {
    private final JPAQueryFactory queryFactory;

    public List<ProfileResponse> findAllByIsDeletedFalse(){
        QUser qUser = QUser.user;
        return queryFactory
                .select(
                        new QProfileResponse(
                               qUser.id,
                               qUser.username,
                                qUser.email,
                                qUser.name,
                                qUser.role,
                                qUser.createdAt
                        )
                )
                .from(qUser)
                .where(qUser.isDeleted.isFalse())
                .fetch();
    }
}
