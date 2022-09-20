package ru.todolist.util;

import org.springframework.ui.Model;
import ru.todolist.model.User;

import javax.servlet.http.HttpSession;

public class AuthUserUtil {

    public static boolean isAuth(HttpSession httpSession) {
        User user = SessionUtil.reg(httpSession);
        return user.getId() != 0 && user.getName() != null && user.getLogin() != null && user.getPassword() != null;
    }
    public static boolean isAuth(User user) {
        return user.getId() != 0 && !user.getName().equals("Гость") && user.getLogin() != null && user.getPassword() != null;
    }

    public static boolean userModelAdd(Model model, HttpSession httpSession) {
        User regUser = SessionUtil.reg(httpSession);
        boolean res = false;
        if (isAuth(regUser)) {
            res = true;
            model.addAttribute("user", regUser);
        }
        model.addAttribute("isUserAuth", res);
        return res;
    }
}
