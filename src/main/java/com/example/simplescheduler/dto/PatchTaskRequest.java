package com.example.simplescheduler.dto;

import lombok.Getter;

@Getter
public class PatchTaskRequest {

    private String password;

    private String task;

    private String writer;
}
