package com.mytasks.api.controllers;

import com.mytasks.api.dtos.TaskHandleCompletedRecordDto;
import com.mytasks.api.dtos.TaskRecordDto;
import com.mytasks.api.models.TaskModel;
import com.mytasks.api.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<TaskModel> handleCompletedTask(@RequestBody TaskHandleCompletedRecordDto taskRecordDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.handleCompleted(taskRecordDto));
    }

    @DeleteMapping
    public ResponseEntity<TaskModel> deleteTask(@RequestBody TaskHandleCompletedRecordDto taskRecordDto) {

        TaskModel task = taskService.deleteTask(taskRecordDto);

        return ResponseEntity.ok().build();
    }
}
