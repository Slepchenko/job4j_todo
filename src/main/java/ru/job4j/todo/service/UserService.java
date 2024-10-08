package ru.job4j.todo.service;

import ru.job4j.todo.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> save(User todoUser);

    Optional<User> findByLoginAndPassword(String login, String password);

    String findUserNameById(int id);

}
