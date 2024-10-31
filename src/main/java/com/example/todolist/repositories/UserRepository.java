package com.example.todolist.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.todolist.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
}
