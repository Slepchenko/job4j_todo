package ru.job4j.todo.repository;

import ru.job4j.todo.model.TodoUser;

import java.util.Optional;

public interface TodoUserRepository {

    Optional<TodoUser> save(TodoUser todoUser);

    Optional<TodoUser> findByLoginAndPassword(String login, String password);

    String findUserNameById(int id);

}
