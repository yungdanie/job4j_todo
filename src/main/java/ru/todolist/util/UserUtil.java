package ru.todolist.util;

import org.springframework.ui.Model;
import ru.todolist.model.User;

import javax.servlet.http.HttpSession;

public class UserUtil {

    public static boolean isAuth(HttpSession httpSession) {
        User user = SessionUtil.reg(httpSession);
        return isAuth(user);
    }
    public static boolean isAuth(User user) {
        return user != null && user.getId() != 0 && user.getLogin() != null && user.getPassword() != null;
    }

    public static User addModelUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean res = isAuth(user);
        model.addAttribute("user", user);
        model.addAttribute("isUserAuth", res);
        return user;
    }

    public static User addModelUser(Model model, User user) {
        boolean res = isAuth(user);
        model.addAttribute("user", user);
        model.addAttribute("isUserAuth", res);
        return user;
    }

    public static void addSessionAndModelUser(Model model, HttpSession httpSession, User user) {
        httpSession.setAttribute("user", user);
        addModelUser(model, user);
    }

    public static void logoutUser(HttpSession session) {
        session.setAttribute("user", new User());
    }
}
