package ru.job4j.todo.repository;

import ru.job4j.todo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findByName(String name);

    Optional<Role> findById(int id);

    List<Role> findAll();

    Role save(Role role);

    boolean deleteById(int id);

    boolean update(Role role);

}
