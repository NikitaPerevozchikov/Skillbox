package main.controllers;

import main.model.Task;
import main.model.TaskService;
import main.model.TaskUpdateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class DefaultController {
    private TaskService taskService;

    @Value("${spring.datasource.url}")
    private String springDatasourceUrl;

    @Value("${spring.datasource.username}")
    private String springDatasourceUsername;

    @Value("${spring.datasource.password}")
    private String springDatasourcePassword;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String springJpaHibernateDdlAuto;

    @Value("${server.port}")
    private String serverPort;

    @Value("@{spring.mvc.hiddenmethod.filter.enabled}")
    private String springMvcHiddenmethodFilterEnabled;


    public DefaultController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String index(Model model) {
        List<Task> tasks = new ArrayList<>(taskService.getAllTask());
        model.addAttribute("tasksLIst", tasks);
        model.addAttribute("count", tasks.size());
        model.addAttribute("springDatasourceUrl", springDatasourceUrl);
        model.addAttribute("springDatasourceUsername", springDatasourceUsername);
        return "index";
    }

    @GetMapping("/tasks/new")
    public String listAddTask(Model model) {
        model.addAttribute("result", new Task());
        return "madeTask";
    }

    @PostMapping("/tasks")
    public String addTask(Task task, Model model) {
        model.addAttribute("result", task);
        taskService.add(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}")
    public String task(@PathVariable int id, Model model) {
        Task task = taskService.get(id);
        model.addAttribute("task", task);
        model.addAttribute("title", "Задача №" + task.getId());
        return "task";
    }

    @DeleteMapping("tasks/{id}")
    public String deleteTask(@PathVariable int id, Model model) {
        taskService.delete(id);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}/create")
    public String listCreateTask(@PathVariable int id, Model model) {
        Task task = taskService.get(id);
        model.addAttribute("task", task);
        model.addAttribute("result", new TaskUpdateRequest());
        return "createTask";
    }

    @PatchMapping("tasks/{id}/create")
    public String createTask(@PathVariable int id, TaskUpdateRequest taskUpdateRequest, Model model) {
        model.addAttribute("result", taskUpdateRequest);
        taskService.create(id, taskUpdateRequest);
        return "redirect:/tasks";
    }
}

