package com.example.simplescheduler.controller;

import com.example.simplescheduler.dto.CreateTaskRequest;
import com.example.simplescheduler.dto.DeleteTaskRequest;
import com.example.simplescheduler.dto.PatchTaskRequest;
import com.example.simplescheduler.dto.TaskResponse;
import com.example.simplescheduler.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody CreateTaskRequest request
    ) {
        TaskResponse response = taskService.createTask(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTask()
    {
        List<TaskResponse> responses = taskService.getAllTask();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(
            @PathVariable Long id
    ) {
        TaskResponse response = taskService.getTask(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> patchTask(
            @PathVariable Long id,
            @RequestBody PatchTaskRequest request
    ) { // TODO Valid
        TaskResponse response = taskService.patchTask(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id,
            @RequestBody DeleteTaskRequest request
    ) {
        taskService.deleteTask(id, request);
        return ResponseEntity.ok().build();
    }

}
