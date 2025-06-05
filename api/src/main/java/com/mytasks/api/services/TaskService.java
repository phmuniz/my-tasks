package com.mytasks.api.services;

import com.mytasks.api.dtos.TaskHandleCompletedRecordDto;
import com.mytasks.api.dtos.TaskRecordDto;
import com.mytasks.api.models.TaskModel;
import com.mytasks.api.repositories.TaskRepository;
import com.mytasks.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public TaskModel saveTask(TaskRecordDto taskRecordDto) {

        TaskModel task = new TaskModel();

        task.setDescription(taskRecordDto.description());
        task.setCompleted(false);
        task.setUser(userRepository.findById(taskRecordDto.user_id()).isPresent() ? userRepository.findById(taskRecordDto.user_id()).get() : null);

        return taskRepository.save(task);
    }
    @Transactional
    public TaskModel handleCompleted(TaskHandleCompletedRecordDto taskHandleCompletedRecordDto) {

        TaskModel task = taskRepository.findById(taskHandleCompletedRecordDto.taskId()).isPresent() ? taskRepository.findById(taskHandleCompletedRecordDto.taskId()).get() : null;

        if(task != null) {
            Boolean completed = task.getCompleted();
            task.setCompleted(!completed);
        }

        return taskRepository.save(task);
    }

    @Transactional
    public TaskModel deleteTask(TaskHandleCompletedRecordDto taskHandleCompletedRecordDto) {

        TaskModel task = taskRepository.findById(taskHandleCompletedRecordDto.taskId()).isPresent() ? taskRepository.findById(taskHandleCompletedRecordDto.taskId()).get() : null;

        taskRepository.delete(task);

        return task;
    }
}
