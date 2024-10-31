package com.example.todolist.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.todolist.models.User;
import com.example.todolist.services.UserService;

public class UserController {
    @Autowired
    private final UserService userService;

	public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<Iterable<User>> searchAll(){
        return ResponseEntity.ok(userService.searchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> searchById(@PathVariable Long id){
        return ResponseEntity.ok(userService.searchById(id));
    }

    @PostMapping
	public ResponseEntity<User> insert(@RequestBody User user) {
		var userCreated = userService.insert(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> atualizar(@PathVariable Long id, @RequestBody User user) {
		userService.update(id, user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.ok().build();
	}
}
