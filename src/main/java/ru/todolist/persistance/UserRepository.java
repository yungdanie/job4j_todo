package ru.todolist.persistance;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.todolist.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@AllArgsConstructor
@Repository
public class UserRepository {

    private final SessionFactory sf;

    private  <T> T tx(Function<Session, T> command) {
        var session = sf.openSession();
        try (session) {
            var tx = session.beginTransaction();
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (Exception e) {
            var tx = session.getTransaction();
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    private void tx(Consumer<Session> command) {
        tx(session -> {
            command.accept(session);
            return null;
        });
    }


    private  <T> Optional<T> getUniqResult(String query, Map<String, Object> args, Class<T> tClass) {
        Function<Session, Optional<T>> command = session -> {
            var sq = session.createQuery(query, tClass);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            return sq.uniqueResultOptional();
        };
        return tx(command);
    }

    private <T> List<T> getList(String query, Map<String, Object> args, Class<T> tClass) {
        Function<Session, List<T>> command = session -> {
            var sq = session.createQuery(query, tClass);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            return sq.list();
        };
        return tx(command);
    }

    public <T> List<T> getList(String query, Class<T> tClass) {
        return getList(query, Map.of(), tClass);
    }

    public User add(User user) {
        tx((Consumer<Session>) session -> session.persist(user));
        return user;
    }

    public void update(User user) {
        tx((Function<Session, Object>) session -> session.merge(user));
    }

    public Optional<User> getById(int id) {
        return getUniqResult("from User where id = :fId", Map.of("fId", id), User.class);
    }

    public Optional<User> getById(User user) {
        return getUniqResult("from User where id = :fId", Map.of("fId", user.getId()), User.class);
    }

    public Optional<User> getByLoginAndPassword(User user) {
        return getUniqResult("from User where login = :fLogin and password = :fPassword",
                Map.of("fLogin", user.getLogin(), "fPassword", user.getPassword()),
                User.class);
    }

    public List<User> getAll() {
        return getList("from User", User.class);
    }
}
