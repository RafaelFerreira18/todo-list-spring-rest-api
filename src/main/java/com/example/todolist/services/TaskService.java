package com.example.todolist.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.todolist.models.Task;
import com.example.todolist.repositories.TaskRepository;
import com.example.todolist.repositories.UserRepository;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    
    public Iterable<Task> searchAllInUser(String email){
        if(userRepository.existsByEmail(email)){
            return taskRepository.findByUserEmail(email);
        }
        else{
            throw new IllegalArgumentException("User doesn't have any tasks");
        }
    }

    public Task searchById(Long id) {
		Optional<Task> task = taskRepository.findById(id);
		return task.get();
	}

	public void update(Long id, Task task) {
		Optional<Task> taskBd = taskRepository.findById(id);
        if(taskBd.isPresent()){
            taskRepository.save(task);
        }
	}

	public void delete(Long id) {
		taskRepository.deleteById(id);
	}

    public Task insert(Task task) {
        if(taskRepository.existsByTitle(task.getTitle())){
			throw new IllegalArgumentException("This task already exists!");
		}
        return this.taskRepository.save(task);
    }
}
