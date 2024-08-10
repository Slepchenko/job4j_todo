package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.todo.service.SimpleTaskService;

@Controller
@AllArgsConstructor
public class IndexController {

    @GetMapping("/index")
    public String getIndex(Model model) {
        return "index";
    }

}
