package com.example.taskflow.domain.task.dto;

import com.example.taskflow.domain.user.entity.User;
import lombok.Getter;

@Getter
public class Assignee {
    private Long id;
    private String username;
    private String name;
    private String email;

    public static Assignee of(User user) {
        Assignee assignee = new Assignee();
        assignee.id = user.getId();
        assignee.username = user.getUsername();
        assignee.name = user.getName();
        assignee.email = user.getEmail();
        return assignee;
    }
}
