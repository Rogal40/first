package com.example.Spring_LAB_5.service;

import com.example.Spring_LAB_5.entity.Category;
import com.example.Spring_LAB_5.entity.Task;
import com.example.Spring_LAB_5.entity.User;
import com.example.Spring_LAB_5.repository.CategoryRepository;
import com.example.Spring_LAB_5.repository.TaskRepository;
import com.example.Spring_LAB_5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Task> getTasksForUser(String username) {
        return taskRepository.findByUser_Username(username);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public void assignTaskToUser(Task task, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());
        task.setCategory(updatedTask.getCategory());
        taskRepository.save(task);
    }

    public void assignCategoryToTask(Task task, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        task.setCategory(category);
        taskRepository.save(task);
    }
}
