package com.example.todolist.repositories;
import org.springframework.data.repository.CrudRepository;

import com.example.todolist.models.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
    boolean existsByTitle(String title);
}
