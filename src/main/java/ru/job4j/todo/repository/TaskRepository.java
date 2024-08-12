package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepository {

    Optional<Task> findById(int id);

    Collection<Task> findAll();

    Collection<Task> findDone();

    Collection<Task> findNew();

    Task save(Task task);

    boolean deleteById(int id);

    boolean update(Task task);

    boolean changeStatusToTrue(int id);


}
