package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.filter.AddUserModel;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.PriorityService;
import ru.job4j.todo.service.TaskService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final PriorityService priorityService;

    @GetMapping("/allTasks")
    public String tasks(Model model, HttpSession session) {
        AddUserModel.checkInMenu(model, session);
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("priorities", priorityService.findAll());
        return "tasks/tasks";
    }

    @GetMapping("/newTasks")
    public String newTasks(Model model, HttpSession session) {
        AddUserModel.checkInMenu(model, session);
        model.addAttribute("tasks", taskService.findNew());
        model.addAttribute("priorities", priorityService.findAll());
        return "tasks/tasks";
    }

    @GetMapping("/doneTasks")
    public String doneTasks(Model model, HttpSession session) {
        AddUserModel.checkInMenu(model, session);
        model.addAttribute("tasks", taskService.findDone());
        model.addAttribute("priorities", priorityService.findAll());
        return "tasks/tasks";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Task task,
                       @RequestParam(name = "priority_status", defaultValue = "false") boolean isUrgentlyTask,
                       Model model, HttpSession session) {
        AddUserModel.checkInMenu(model, session);
        task.setUser((User) session.getAttribute("user"));
        if (isUrgentlyTask) {
            task.setPriority(priorityService.getPriorityByName("urgently").get());
        } else {
            task.setPriority(priorityService.getPriorityByName("normal").get());
        }
        taskService.save(task);
        return "redirect:/tasks/allTasks";
    }

    @GetMapping("/{id}")
    public String task(Model model, HttpSession session, @PathVariable int id) {
        AddUserModel.checkInMenu(model, session);
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()) {
            return "tasks/tasks";
        }
        Task task = optionalTask.get();
        model.addAttribute("task", task);
        model.addAttribute("responsible", task.getUser().getName());
        model.addAttribute("priority", task.getPriority().getName());
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
    public String updatePage(Model model, HttpSession session, @PathVariable int id) {
        AddUserModel.checkInMenu(model, session);
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()) {
            return "tasks/tasks";
        }
        model.addAttribute("task", optionalTask.get());
        return "/tasks/update";
    }

    @PostMapping("/update")
    public String update(Model model,
                         HttpSession session,
                         @ModelAttribute Task task,
                         @RequestParam(name = "priority_status", defaultValue = "false") boolean isUrgentlyTask) {
        AddUserModel.checkInMenu(model, session);
        if (isUrgentlyTask) {
            task.setPriority(priorityService.getPriorityByName("urgently").get());
        } else {
            task.setPriority(priorityService.getPriorityByName("normal").get());
        }
        boolean isUpdated = taskService.update(task);
        if (!isUpdated) {
            return "tasks/tasks";
        }
        return "redirect:/tasks/" + task.getId();
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
