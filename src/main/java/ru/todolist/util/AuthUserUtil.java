package ru.todolist.util;

import ru.todolist.model.User;

public class AuthUserUtil {
    public static boolean auth(User user) {
        return user.getId() != 0 && !user.getName().equals("Гость") && user.getLogin() != null && user.getPassword() != null;
    }
}
