package com.example.taskflow.domain.task.repository;

import com.example.taskflow.domain.task.entity.Task;
import com.example.taskflow.domain.task.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @EntityGraph(attributePaths = {"assignee"})
    Optional<Task> findByIdAndIsDeletedIsFalse(Long id);
    @EntityGraph(attributePaths = {"assignee"})
    Page<Task> findAllByStatusAndIsDeletedIsFalse(Status status, Pageable pageable);
}
