package com.example.simplescheduler.service;

import com.example.simplescheduler.dto.CreateTaskRequest;
import com.example.simplescheduler.dto.DeleteTaskRequest;
import com.example.simplescheduler.dto.PatchTaskRequest;
import com.example.simplescheduler.dto.TaskResponse;
import com.example.simplescheduler.entity.Task;
import com.example.simplescheduler.exeption.TaskException;
import com.example.simplescheduler.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskResponse createTask(CreateTaskRequest request)
    {
        Task task = new Task(request.getTask(), request.getWriter(), request.getPassword());
        // TODO
        Task saveTask = taskRepository.save(task);

        return TaskResponse.of(saveTask);
    }

    public List<TaskResponse> getAllTask()
    {
        List<Task> taskList = taskRepository.findAll();
        // TODO 람다식 사용?

//        // dto타입의 빈 배열 만들기
//        List<CreateTaskResponse> dtoList = new ArrayList<>();
//
//        // for문에서 task -> taskDto & list.append()
//        for (Task task : taskList) {
//            CreateTaskResponse res = new CreateTaskResponse(task.getTask(), task.getWriter(), task.getPassword(), task.getCreatedAt(), task.getUpdatedAt());
//            dtoList.add(res);
//        }
//
//        // return dto list
//        return dtoList;

        return taskList.stream().map(TaskResponse::of).toList();
    }

    public TaskResponse getTask(Long id)
    {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new TaskException("존재하지 않는 일정입니다."));

        return TaskResponse.of(task);
    }

    public TaskResponse patchTask(Long id, PatchTaskRequest request)
    {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new TaskException("존재하지 않는 일정입니다."));

        // 비밀번호 검사
        if (!task.getPassword().equals(request.getPassword())) {
            throw new TaskException("비밀번호가 일치하지 않습니다.");
        }

        task.setTask(request.getTask());
        task.setWriter(request.getWriter());

        taskRepository.save(task);

        return TaskResponse.of(task);
    }

    public void deleteTask(Long id, DeleteTaskRequest request)
    {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new TaskException("존재하지 않는 일정입니다."));

        // 비밀번호 검사
        if (!task.getPassword().equals(request.getPassword())) {
            throw new TaskException("비밀번호가 일치하지 않습니다.");
        }

        taskRepository.delete(task);
    }
}
