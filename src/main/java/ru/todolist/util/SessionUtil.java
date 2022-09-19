package ru.todolist.util;

import ru.todolist.model.User;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static User reg(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        return user;
    }
}
