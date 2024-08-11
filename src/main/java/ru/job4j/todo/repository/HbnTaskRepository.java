package ru.job4j.todo.repository;

//import lombok.AllArgsConstructor;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.stereotype.Repository;
//import ru.job4j.todo.model.Task;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnTaskRepository implements TaskRepository {

    private final SessionFactory sf;

    @Override
    public Optional<Task> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Task> findAll() {
        try (Session session = sf.openSession()) {
            List<Task> tasks;
            session.beginTransaction();
            tasks = session.createQuery("from Task", Task.class)
                    .getResultList();
            session.getTransaction().commit();
            return List.copyOf(tasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public Collection<Task> findDone() {
        try (Session session = sf.openSession()) {
            List<Task> tasks;
            session.beginTransaction();
            tasks = session.createQuery("from Task where done = true", Task.class)
                    .getResultList();
            session.getTransaction().commit();
            return List.copyOf(tasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public Collection<Task> findNew() {
        try (Session session = sf.openSession()) {
            List<Task> tasks;
            session.beginTransaction();
            tasks = session.createQuery("from Task where done = false", Task.class)
                    .getResultList();
            session.getTransaction().commit();
            return List.copyOf(tasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public Task save(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
