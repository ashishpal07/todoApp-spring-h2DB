package com.scaler.todoApp.task;

import com.scaler.todoApp.common.ErrorResponseDto;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        List<TaskEntity> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> createNewTask(@RequestBody TaskDto task) {
        TaskEntity newTask = taskService.createNewTask(task);
        return ResponseEntity.ok(newTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Long id) {
        TaskEntity task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTaskById(@PathVariable Long id, @RequestBody TaskDto task) {
        TaskEntity updatedTask = taskService.updateTaskById(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @ExceptionHandler({
            TaskService.TaskInvalidException.class,
            TaskService.TaskAlreadyExistException.class,
            TaskService.TaskNotFoundException.class
    })
    ResponseEntity<ErrorResponseDto> handleError(Exception e) {

        if(e instanceof TaskService.TaskNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto(e.getMessage()));
        }

        if (e instanceof TaskService.TaskAlreadyExistException) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponseDto(e.getMessage()));
        }

        if (e instanceof TaskService.TaskInvalidException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDto(e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto(e.getMessage()));

    }

}
