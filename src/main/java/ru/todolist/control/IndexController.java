package ru.todolist.control;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.todolist.service.TaskService;
import ru.todolist.util.SessionUtil;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@ThreadSafe
public class IndexController {
    private final TaskService taskService;

    @GetMapping("/index")
    public String index(Model model, HttpSession httpSession) {
        model.addAttribute("user", SessionUtil.reg(httpSession));
        model.addAttribute("allTasks", taskService.getAll());
        return "index";
    }
}
