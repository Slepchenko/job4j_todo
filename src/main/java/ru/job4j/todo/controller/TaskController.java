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
    public String save(Model model, @ModelAttribute Task task) {
        try {
            taskService.save(task);
            return "redirect:/tasks/allTasks";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/{id}")
    public String task(Model model, @PathVariable int id) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()) {
            model.addAttribute("message", "Задача не найдена");
            return "errors/404";
        }
        model.addAttribute("task", optionalTask.get());
        return "tasks/task";
    }

    @GetMapping("/changeStatus/{id}")
    public String changeStatus(@PathVariable int id, Model model) {
        boolean isChangedStatus = taskService.changeStatusToTrue(id);
        if (!isChangedStatus) {
            model.addAttribute("message", "Изменить статус выполнения задачи не удалось");
            return "tasks/tasks";
        }
        return "redirect:/tasks/allTasks";
    }

    @GetMapping("/updatePage/{id}")
    public String updatePage(Model model, @PathVariable int id) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()) {
            model.addAttribute("message", "Задача не найдена");
            return "tasks/tasks";
        }
        model.addAttribute("task", optionalTask.get());
        return "/tasks/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model) {
        try {
            boolean isUpdated = taskService.update(task);
            if (!isUpdated) {
                model.addAttribute("message", "Редактирование не удалось");
                return "errors/404";
            }
            return "redirect:/tasks/allTasks";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        boolean isDeleted = taskService.delete(id);
        if (!isDeleted) {
            model.addAttribute("message", "Удаление не удалось");
            return "tasks/tasks";
        }
        return "redirect:/tasks/allTasks";
    }

}
