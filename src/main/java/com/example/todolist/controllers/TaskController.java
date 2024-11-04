package com.example.todolist.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.todolist.models.Task;
import com.example.todolist.services.TaskService;
import com.example.todolist.services.UserService;
import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("tasks")
public class TaskController {
    TaskService taskService;
    UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Iterable<Task>> searchAllInUser (@PathVariable String email) {
        return ResponseEntity.ok(taskService.searchAllInUser(email));
    }

    @GetMapping("/{id}")
    public Task searchById(@RequestParam Long id) {
        return taskService.searchById(id);
    }

    @PostMapping
    public ResponseEntity<Task> insert(@RequestBody Task task) {
        try {
            Task taskCreated = taskService.insert(task);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(taskCreated.getId()).toUri();
            return ResponseEntity.created(location).body(taskCreated);    
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable long id, @RequestBody Task task) {
        taskService.update(id, task);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
