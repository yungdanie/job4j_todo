package ru.todolist.control;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.todolist.model.User;
import ru.todolist.service.UserService;
import ru.todolist.util.AuthUserUtil;
import ru.todolist.util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/authUser")
    public String authUserForm(@RequestParam(name = "fail", required = false) Boolean fail, HttpSession httpSession) {
        User user = SessionUtil.reg(httpSession);
        if (AuthUserUtil.auth(user)) {
            return "redirect:index";
        }
        return "loginPage";
    }

    @PostMapping("/authUser")
    public String authUser(@ModelAttribute User user, HttpSession session) {
        Optional<User> loggedUser = userService.getByLoginAndPassword(user);
        if (loggedUser.isEmpty()) {
            return "redirect:/authUser?fail=true";
        }
        session.setAttribute("user", loggedUser.get());
        return "index";
    }
}
