package ru.job4j.todo.filter;

import org.springframework.ui.Model;
import ru.job4j.todo.model.TodoUser;

import javax.servlet.http.HttpSession;

public class AddUserModel {

    public static Model checkInMenu(Model model, HttpSession session) {
        TodoUser user = (TodoUser) session.getAttribute("user");
        if (user == null) {
            user = new TodoUser();
            user.setName("Гость");
        }
        return model.addAttribute("user", user);
    }

}
