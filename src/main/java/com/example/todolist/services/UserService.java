package com.example.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todolist.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

}
