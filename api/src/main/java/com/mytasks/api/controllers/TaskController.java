package com.mytasks.api.controllers;

import com.mytasks.api.dtos.TaskRecordDto;
import com.mytasks.api.models.TaskModel;
import com.mytasks.api.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskModel> saveTask(@RequestBody TaskRecordDto taskRecordDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(taskRecordDto));
    }
}
