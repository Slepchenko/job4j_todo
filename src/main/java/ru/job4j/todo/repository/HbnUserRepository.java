package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnUserRepository implements UserRepository {

    private final SessionFactory sf;

    @Override
    public Optional<User> save(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        try (Session session = sf.openSession()) {
            User user;
            session.beginTransaction();
            user = session.createQuery("from User where login = :fLogin and password = :fPassword", User.class)
                    .setParameter("fLogin", login)
                    .setParameter("fPassword", password)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public String findUserNameById(int id) {
        try (Session session = sf.openSession()) {
            User user;
            session.beginTransaction();
            user = session.createQuery("from User where id = :fId", User.class)
                    .setParameter("fId", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return user.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
