package ru.todolist.util;

import org.springframework.ui.Model;
import ru.todolist.model.User;

import javax.servlet.http.HttpSession;

public class AuthUserUtil {

    public static boolean isAuth(HttpSession httpSession) {
        User user = SessionUtil.reg(httpSession);
        return isAuth(user);
    }
    public static boolean isAuth(User user) {
        return user != null && user.getId() != 0 && user.getLogin() != null && user.getPassword() != null;
    }

    public static User addModelUser(Model model, HttpSession session) {
        boolean res = false;
        User user = (User) session.getAttribute("user");
        if (isAuth(user)) {
                res = true;
        }
        model.addAttribute("user", user);
        model.addAttribute("isUserAuth", res);
        return user;
    }
}
