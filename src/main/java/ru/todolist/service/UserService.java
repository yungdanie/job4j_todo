package ru.todolist.service;


import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import ru.todolist.model.User;
import ru.todolist.persistance.UserHBStore;

import java.util.Optional;

@AllArgsConstructor
public class UserService {

    private final UserHBStore store;

    public User add(User user) throws ConstraintViolationException {
        return store.add(user);
    }

    public Optional<User> getById(User user) {
        return store.getById(user);
    }

    public Optional<User> getByLoginAndPassword(User user) {
        return store.getByLoginAndPassword(user);
    }
}
