package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.service.SimpleTaskService;

@Controller
public class TaskController {

    private final SimpleTaskService simpleTaskService;

    public TaskController(SimpleTaskService simpleTaskService) {
        this.simpleTaskService = simpleTaskService;
    }


    @GetMapping("/index")
    public String getIndex(Model model) {
        return "index";
    }

}
