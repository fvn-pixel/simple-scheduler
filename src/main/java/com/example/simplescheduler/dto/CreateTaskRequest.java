package com.example.simplescheduler.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateTaskRequest {

    @NotNull
    private String task;

    private String writer;

    private String password;
}
