package ru.job4j.todo.service;

import ru.job4j.todo.model.TodoUser;

import java.util.Optional;

public interface TodoUserService {

    Optional<TodoUser> save(TodoUser todoUser);

    Optional<TodoUser> findByLoginAndPassword(String login, String password);

    String findUserNameById(int id);

}
