package ru.todolist.control;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.todolist.model.User;
import ru.todolist.service.TaskService;
import ru.todolist.util.AuthUserUtil;
import ru.todolist.util.SessionUtil;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@ThreadSafe
public class IndexController {
    private final TaskService taskService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        AuthUserUtil.addModelUser(model, session);
        model.addAttribute("allTasks", taskService.getAll());
        return "index";
    }
}
