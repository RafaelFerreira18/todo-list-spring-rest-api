package com.example.todolist.services;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todolist.models.User;

import com.example.todolist.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> searchAll(){
        return userRepository.findAll();
    }

    public User searchById(Long id){
        return userRepository.findById(id).get();
    }

    public void update(Long id, User user){
        Optional<User> userBd = userRepository.findById(id);
        if(userBd.isPresent()){
            userRepository.save(user);
        }
    }

    public void delete(Long id) {
		userRepository.deleteById(id);
	}

    public User insert(User user){
        if(userRepository.existsByEmail(user.getEmail())){
			throw new IllegalArgumentException("This email already exists");
		}
        return this.userRepository.save(user);
    }
}
