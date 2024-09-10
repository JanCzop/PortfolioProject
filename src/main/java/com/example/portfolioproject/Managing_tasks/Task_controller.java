package com.example.portfolioproject.Managing_tasks;

import com.example.portfolioproject.Entities.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class Task_controller {

    private final Task_service task_service;

    public Task_controller(Task_service task_service) {
        this.task_service = task_service;
    }

    @PostMapping
    public ResponseEntity<Task> create_task(@RequestBody Task_DTO task_dto) {
        Task task = task_service.create_task(task_dto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<Task> get_task(@PathVariable Long task_id) {
        Task task = task_service.get_task(task_id);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<Task>> get_all_tasks() {
        List<Task> tasks = task_service.get_all_tasks();
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<String> delete_task(@PathVariable Long task_id) {
        task_service.delete_task(task_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task with ID: " + task_id + "deleted successfully");
    }

    @PutMapping("/{task_id}")
    public ResponseEntity<Task> update_task(@PathVariable Long task_id, @RequestBody Task_DTO task_dto) {
        Task updatedTask = task_service.update_task(task_id, task_dto);
        return ResponseEntity.ok(updatedTask);
    }
}
