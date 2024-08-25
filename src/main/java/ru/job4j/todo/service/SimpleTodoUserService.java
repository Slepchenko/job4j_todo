package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.TodoUser;
import ru.job4j.todo.repository.TodoUserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTodoUserService implements TodoUserService {

    private final TodoUserRepository todoUserRepository;

    @Override
    public Optional<TodoUser> save(TodoUser todoUser) {
        return todoUserRepository.save(todoUser);
    }

    @Override
    public Optional<TodoUser> findByLoginAndPassword(String login, String password) {
        return todoUserRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public String findUserNameById(int id) {
        return todoUserRepository.findUserNameById(id);
    }
}
