package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

import java.util.Optional;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

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

    @PostMapping("/save")
    public String save(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks/allTasks";
    }

    @GetMapping("/{id}")
    public String task(Model model, @PathVariable int id) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()) {
            return "tasks/tasks";
        }
        model.addAttribute("task", optionalTask.get());
        return "tasks/task";
    }

    @GetMapping("/changeStatus/{id}")
    public String changeStatus(@PathVariable int id) {
        boolean isChangedStatus = taskService.changeStatusToTrue(id);
        if (!isChangedStatus) {
            return "tasks/tasks";
        }
        return "redirect:/tasks/allTasks";
    }

    @GetMapping("/updatePage/{id}")
    public String updatePage(Model model, @PathVariable int id) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()) {
            return "tasks/tasks";
        }
        model.addAttribute("task", optionalTask.get());
        return "/tasks/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task) {
            boolean isUpdated = taskService.update(task);
            if (!isUpdated) {
                return "tasks/tasks";
            }
            return "tasks/task";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        boolean isDeleted = taskService.delete(id);
        if (!isDeleted) {
            return "tasks/tasks";
        }
        return "redirect:/tasks/allTasks";
    }

}
