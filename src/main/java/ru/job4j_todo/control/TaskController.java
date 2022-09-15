package ru.job4j_todo.control;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j_todo.model.Task;
import ru.job4j_todo.service.TaskService;

import java.util.Optional;


@Controller
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/allTasks")
    public String allTasks(Model model) {
        model.addAttribute("allTasks", taskService.getAll());
        return "allTasksForm";
    }

    @GetMapping("/allNewTasks")
    public String allNewTasks(Model model) {
        model.addAttribute("newTasks", taskService.getNew());
        return "allNewTasksForm";
    }

    @GetMapping("/allDoneTasks")
    public String allDoneTasks(Model model) {
        model.addAttribute("doneTasks", taskService.getDone());
        return "allDoneTasksForm";
    }

    @GetMapping("/addTask")
    public String addTaskForm() {
        return "addTaskForm";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute Task task) {
        taskService.add(task);
        return "redirect:index";
    }

    @GetMapping("/editTask/{taskID}")
    public String updateTask(@PathVariable("taskID") int taskID, Model model) {
        Optional<Task> optionalTask = taskService.findById(taskID);
        if (optionalTask.isEmpty()) {
            model.addAttribute("errorMessage", "Пользователь не найден");
            return "errorPage";
        }
        model.addAttribute("task", optionalTask.get());
        return "editTaskPage";
    }

    @PostMapping("/editTask")
    public String editTask(@ModelAttribute Task task) {
        return "redirect:index";
    }
}
