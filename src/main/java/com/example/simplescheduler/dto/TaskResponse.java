package com.example.simplescheduler.dto;

import com.example.simplescheduler.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TaskResponse {

    private Long id;

    private String task;

    private String writer;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static TaskResponse of(Task task){
        return new TaskResponse(
                task.getId(), task.getTask(), task.getWriter(),
                task.getCreatedAt(), task.getUpdatedAt()
        );
    }
}
