package ru.job4j_todo.control;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j_todo.service.TaskService;

@Controller
@AllArgsConstructor
@ThreadSafe
public class IndexController {
    private final TaskService taskService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("allTasks", taskService.getAll());
        return "index";
    }
}
