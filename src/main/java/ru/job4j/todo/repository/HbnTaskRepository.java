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
        try (Session session = sf.openSession()) {
            Task task;
            session.beginTransaction();
            task = session.createQuery("from Task where id = :fId", Task.class)
                    .setParameter("fId", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Task> findAll() {
        try (Session session = sf.openSession()) {
            List<Task> tasks;
            session.beginTransaction();
            tasks = session.createQuery("from Task order by created", Task.class)
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
            tasks = session.createQuery("from Task where done = true order by created", Task.class)
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
            tasks = session.createQuery("from Task where done = false order by created", Task.class)
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

    @Override
    public boolean update(Task task) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery("update from Task set name = :fName, description = :fDescription where id = :fId")
                    .setParameter("fId", task.getId())
                    .setParameter("fName", task.getName())
                    .setParameter("fDescription", task.getDescription())
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean changeStatusToTrue(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery("update from Task set done = true where id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
