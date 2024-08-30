package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.filter.AddUserModel;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class IndexController {

    @GetMapping("/index")
    public String getIndex(Model model, HttpSession session) {
        AddUserModel.checkInMenu(model, session);
        return "/index";
    }

}
