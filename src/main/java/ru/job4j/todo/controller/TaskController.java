package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute Task task) {
        if (task == null) {
            model.addAttribute("message", "Ошибка добавления задания");
            return "errors/404";
        }
        taskService.save(task);
        return "redirect:/tasks/allTasks";

    }

    @GetMapping("/allTasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/tasks";
    }

    @GetMapping("/newTasks")
    public String newTasks(Model model) {
        model.addAttribute("tasks", taskService.findNew());
        return "tasks/tasks";
    }

    @GetMapping("/doneTasks")
    public String doneTasks(Model model) {
        model.addAttribute("tasks", taskService.findDone());
        return "tasks/tasks";
    }

}
