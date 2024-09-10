package ru.job4j.todo.repository;

import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityRepository {

    List<Priority> findAll();

    Optional<Priority> getPriorityByName(String name);

}
