package com.example.Spring_LAB_5.controller;

import com.example.Spring_LAB_5.entity.Task;
import com.example.Spring_LAB_5.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String listTasks(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("tasks", taskService.getTasksForUser(userDetails.getUsername()));
        return "tasks";
    }

    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@Valid @ModelAttribute("task") Task task, BindingResult result,
                          @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "add-task";
        }
        taskService.assignTaskToUser(task, userDetails.getUsername());
        return "redirect:/tasks";
    }
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/tasks";
    }
    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "edit-task";
    }
    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        taskService.updateTask(id, task);
        return "redirect:/tasks";
    }

}
