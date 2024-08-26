package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.TodoUser;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnTodoUserRepository implements TodoUserRepository {

private final CrudRepository crudRepository;

    @Override
    public Optional<TodoUser> save(TodoUser todoUser) {
        crudRepository.run(session -> session.persist(todoUser));
        return Optional.of(todoUser);
    }

    @Override
    public Optional<TodoUser> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional("from TodoUser where login = :fLogin and password = :fPassword", TodoUser.class,
                Map.of("fLogin", login, "fPassword", password));
    }

    @Override
    public String findUserNameById(int id) {
        return crudRepository.optional("from TodoUser where id = :fId", TodoUser.class, Map.of("fId", id))
                .get()
                .getName();
    }
}
