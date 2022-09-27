package ru.todolist.util;

import ru.todolist.model.User;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static User reg(HttpSession httpSession) {
        return (User) httpSession.getAttribute("user");
    }
}
