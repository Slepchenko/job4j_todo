package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.todo.model.TodoUser;
import ru.job4j.todo.service.TodoUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/todo_users")
@AllArgsConstructor
public class TodoUserController {

    private final TodoUserService userService;

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "/todo_users/register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute TodoUser user) {
        Optional<TodoUser> optionalUser = userService.save(user);
        if (optionalUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "errors/404";
        }
        return "/todo_users/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/todo_users/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute TodoUser user, Model model, HttpServletRequest request) {
        Optional<TodoUser> optionalUser = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
        if (optionalUser.isEmpty()) {
            model.addAttribute("error", "Логин или пароль введены неверно");
            return "/todo_users/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", optionalUser.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/todo_users/login";
    }

}
