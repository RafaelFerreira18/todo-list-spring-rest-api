package com.example.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todolist.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTitle(String title);
    Iterable<Task> findByUserEmail(String email);
}
