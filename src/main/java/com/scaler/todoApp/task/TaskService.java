package com.scaler.todoApp.task;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    public TaskEntity createNewTask(TaskDto task) {

        if(task.getDueDate() != null && task.getDueDate().before(new Date())) {
            throw new TaskInvalidException("Due date must be in future");
        }

        TaskEntity createTask = new TaskEntity();
        createTask.setDone(task.getDone());
        createTask.setName(task.getName());
        createTask.setDueDate(task.getDueDate());
        return taskRepository.save(createTask);
    }

    public TaskEntity getTaskById(Long id) {
        TaskEntity task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return  task;
    }

    public TaskEntity updateTaskById(Long id, TaskDto task) {
        TaskEntity findTask = taskRepository.findById(id).orElse(null);

        if(findTask != null) {
            if(task.name != null) findTask.setName(task.getName());
            if(task.dueDate != null) findTask.setDueDate(task.getDueDate());
            if(task.done != null) findTask.setDone(task.getDone());
            return taskRepository.save(findTask);
        }

        TaskEntity newTask = new TaskEntity();
        newTask.setName(task.getName());
        newTask.setDueDate(task.getDueDate());
        newTask.setDone(task.getDone());

        return taskRepository.save(newTask);
    }

    static class TaskNotFoundException extends IllegalArgumentException {
        public TaskNotFoundException(Long id) {
            super("Task not found with " + id + " id.");
        }
    }

    static class TaskAlreadyExistException extends IllegalArgumentException {
        public TaskAlreadyExistException(Long id) {
            super("Task already exist with " + id + " id.");
        }
    }

    static class TaskInvalidException extends IllegalArgumentException {
        public TaskInvalidException(String message) {
            super(message);
        }
    }

}
