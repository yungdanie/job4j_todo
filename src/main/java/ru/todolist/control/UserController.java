package ru.todolist.control;


import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.todolist.model.User;
import ru.todolist.service.UserService;
import ru.todolist.util.UserUtil;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/authUser")
    public String authUserForm(@RequestParam(name = "fail", required = false) Boolean fail, Model model) {
        if (fail != null) {
            model.addAttribute("fail", fail);
        }
        return "loginPage";
    }

    @PostMapping("/authUser")
    public String authUser(@ModelAttribute User user, HttpSession session, Model model) {
        Optional<User> loggedUser = userService.getByLoginAndPassword(user);
        if (loggedUser.isEmpty()) {
            return "redirect:/authUser?fail=true";
        }
        UserUtil.addSessionAndModelUser(model, session, loggedUser.get());
        return "redirect:index";
    }


    @GetMapping("/regUser")
    public String registrationUser(@RequestParam(name = "fail", required = false) Boolean fail, Model model) {
        if (fail != null) {
            model.addAttribute("fail", fail);
        }
        return "regPage";
    }
    @PostMapping("/regUser")
    public String authRegUser(@ModelAttribute User user, Model model, HttpSession session) {
        User regUser;
        try {
            regUser = userService.add(user);
        } catch (ConstraintViolationException e) {
            return "redirect:regUser?fail=true";
        }
        UserUtil.addSessionAndModelUser(model, session, regUser);
        model.addAttribute("successMessage", "Регистрация прошла успешно!");
        return "successPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        UserUtil.logoutUser(session);
        return "redirect:authPage";
    }
}
