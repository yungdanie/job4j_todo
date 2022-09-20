package ru.todolist.persistance;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import ru.todolist.model.User;

import java.util.Optional;

@AllArgsConstructor
public class UserHBStore {
    private final SessionFactory sf;

    public User add(User user) throws ConstraintViolationException {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return user;
    }

    public Optional<User> getById(User user) {
        Session session = sf.openSession();
        Transaction tx = null;
        Optional<User> optionalUser;
        try {
            tx = session.beginTransaction();
            optionalUser = session.createQuery("from User where id = :id", User.class)
                    .setParameter("id", user.getId())
                    .uniqueResultOptional();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return optionalUser;
    }

    public Optional<User> getByLoginAndPassword(User user) {
        Session session = sf.openSession();
        Transaction tx = null;
        Optional<User> optionalUser;
        try {
            tx = session.beginTransaction();
            optionalUser = session.createQuery("from User where login = :login AND password = :password ", User.class)
                    .setParameter("login", user.getLogin())
                    .setParameter("password", user.getPassword())
                    .uniqueResultOptional();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return optionalUser;
    }


}
