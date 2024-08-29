package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.RoleService;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnUserRepository implements UserRepository {

    private final CrudRepository crudRepository;

    private final RoleService roleService;

    @Override
    public Optional<User> save(User user) {
        user.setRole(roleService.findByName("user").get());
        crudRepository.run(session -> session.persist(user));
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional("from User where login = :fLogin and password = :fPassword", User.class,
                Map.of("fLogin", login, "fPassword", password));
    }

    @Override
    public String findUserNameById(int id) {
        return crudRepository.optional("from User where id = :fId", User.class, Map.of("fId", id))
                .get()
                .getName();
    }
}
