package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {

    Optional<Task> findById(int id);

    Collection<Task> findAll();

    Collection<Task> findDone();

    Collection<Task> findNew();

    Task save(Task task);

    boolean deleteById(int id);

}
