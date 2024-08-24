package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnUserRepository implements UserRepository {

private final CrudRepository crudRepository;

    @Override
    public Optional<User> save(User user) {
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
        return crudRepository.optional("from Task where id = :fId", User.class, Map.of("fId", id))
                .get()
                .getName();
    }
}
