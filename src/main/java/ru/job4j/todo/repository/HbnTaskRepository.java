package ru.job4j.todo.repository;

//import lombok.AllArgsConstructor;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.stereotype.Repository;
//import ru.job4j.todo.model.Task;

import org.hibernate.SessionFactory;
import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

//@Repository
//@AllArgsConstructor
public class HbnTaskRepository implements TaskRepository {

    private final SessionFactory sf;

    public HbnTaskRepository(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Optional<Task> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Task> findAll() {
        return null;
    }

    @Override
    public Collection<Task> findDone() {
        return null;
    }

    @Override
    public Collection<Task> findNew() {
        return null;
    }

    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
