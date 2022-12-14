package ru.todolist.control;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.todolist.model.Task;
import ru.todolist.service.CategoryService;
import ru.todolist.service.PriorityService;
import ru.todolist.service.TaskService;
import ru.todolist.util.SessionUtil;
import ru.todolist.util.UserUtil;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final PriorityService priorityService;

    private final CategoryService categoryService;

    @GetMapping("/allTasks")
    public String allTasks(Model model, HttpSession session) {
        UserUtil.addModelUser(model, session);
        model.addAttribute("allTasks", taskService.getAll());
        return "allTasksForm";
    }

    @GetMapping("/allNewTasks")
    public String allNewTasks(Model model, HttpSession session) {
        UserUtil.addModelUser(model, session);
        model.addAttribute("newTasks", taskService.getNew());
        return "allNewTasksForm";
    }

    @GetMapping("/allDoneTasks")
    public String allDoneTasks(Model model, HttpSession session) {
        UserUtil.addModelUser(model, session);
        model.addAttribute("doneTasks", taskService.getDone());
        return "allDoneTasksForm";
    }

    @GetMapping("/addTask")
    public String addTaskForm(Model model, HttpSession session) {
        UserUtil.addModelUser(model, session);
        model.addAttribute("priorities", priorityService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "addTaskForm";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute Task task, HttpSession session,
                          @RequestParam("categories") int[] categoriesId) {
        task.setUser(SessionUtil.reg(session));
        taskService.add(task);
        return "redirect:index";
    }

    @GetMapping("/editTask/{taskID}")
    public String updateTask(@PathVariable("taskID") int taskID, Model model, HttpSession session) {
        UserUtil.addModelUser(model, session);
        Optional<Task> optionalTask = taskService.findById(taskID);
        if (optionalTask.isEmpty()) {
            model.addAttribute("errorMessage", "???????????????????????? ???? ????????????");
            return "errorPage";
        }
        model.addAttribute("editTask", optionalTask.get());
        return "editTaskPage";
    }

    @PostMapping("/editTask")
    public String editTask(@ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:index";
    }

    @GetMapping("/tasks/{taskID}")
    public String taskPage(@PathVariable("taskID") int taskID, Model model, HttpSession session) {
        UserUtil.addModelUser(model, session);
        Optional<Task> optionalTask = taskService.findById(taskID);
        if (optionalTask.isEmpty()) {
            model.addAttribute("errorMessage", "???????????????????????? ???? ????????????");
            return "errorPage";
        }
        model.addAttribute("thisTask", optionalTask.get());
        return "taskPage";
    }

    @PostMapping("/executeTask/{taskID}")
    public String executeTask(@PathVariable("taskID") int taskID, Model model, HttpSession session) {
        UserUtil.addModelUser(model, session);
        Optional<Integer> result = taskService.executeDone(taskID);
        if (result.isEmpty()) {
            model.addAttribute("errorMessage", "???????????????? ???????????? ?????? \"??????????????????????\" ???? ????????????????????");
            return "errorPage";
        }
        model.addAttribute("successMessage", "???????????? ?????????????????? ??????????????");
        return "successPage";
    }

    @PostMapping("/deleteTask/{taskID}")
    public String deleteTask(@PathVariable("taskID") int taskID, Model model, HttpSession session) {
        UserUtil.addModelUser(model, session);
        Optional<Integer> result = taskService.delete(taskID);
        if (result.isEmpty()) {
            model.addAttribute("errorMessage", "?????????????? ???????????? ???? ??????????????");
            return "errorPage";
        }
        model.addAttribute("successMessage", "???????????? ?????????????? ??????????????");
        return "successPage";
    }
}
