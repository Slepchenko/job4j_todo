package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnTaskRepository implements TaskRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Task> findById(int id) {
        return crudRepository.optional("from Task where id = :fId", Task.class, Map.of("fId", id));
    }

    @Override
    public Collection<Task> findAll() {
        return crudRepository.query("from Task order by created", Task.class);
    }

    @Override
    public Collection<Task> findDone() {
        return crudRepository.query("from Task where done = true order by created", Task.class);
    }

    @Override
    public Collection<Task> findNew() {
        return crudRepository.query("from Task where done = false order by created", Task.class);
    }

    @Override
    public Task save(Task task) {
        crudRepository.run(session -> session.persist(task));
        return task;
    }

    @Override
    public boolean deleteById(int id) {
        crudRepository.run("delete from Task where id = :fId", Map.of("fId", id));
        return true;
    }

    @Override
    public boolean update(Task task) {
       crudRepository.run(session -> session.merge(task));
       return true;
    }

    @Override
    public boolean changeStatusToTrue(int id) {
        crudRepository.run("update from Task set done = true where id = :fId", Map.of("fId", id));
        return true;
    }

}
