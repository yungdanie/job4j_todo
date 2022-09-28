package ru.todolist.persistance;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
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

    private final GeneralRepository repository;

    public User add(User user) {
        repository.tx((Consumer<Session>) session -> session.persist(user));
        return user;
    }

    public void update(User user) {
        repository.tx((Function<Session, Object>) session -> session.merge(user));
    }

    public Optional<User> getById(int id) {
        return repository.getUniqResult("from User where id = :fId", Map.of("fId", id), User.class);
    }

    public Optional<User> getById(User user) {
        return repository.getUniqResult("from User where id = :fId", Map.of("fId", user.getId()), User.class);
    }

    public Optional<User> getByLoginAndPassword(User user) {
        return repository.getUniqResult("from User where login = :fLogin and password = :fPassword",
                Map.of("fLogin", user.getLogin(), "fPassword", user.getPassword()),
                User.class);
    }

    public List<User> getAll() {
        return repository.getList("from User", User.class);
    }
}
