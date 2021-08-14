package main.controllers;

import main.model.TaskService;
import main.model.Task;
import main.model.TaskUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
//
//    @PostMapping("/tasks")
//    public int add(Task task) {
//        return taskService.add(task);
//    }

//    @GetMapping("/tasks/{id}")
//    public Task get(@PathVariable int id) {
//        return taskService.get(id);
//    }

//    @GetMapping("/tasks")
//    public List<Task> list() {
//        return taskService.getAllTask();
//    }

//    @DeleteMapping("/tasks/{id}")
//    public void delete(@PathVariable int id) {
//        taskService.delete(id);
//    }
//
//    @DeleteMapping("/tasks")
//    public void deleteAll() {
//        taskService.deleteAllTask();
//    }
//
//    @PutMapping("/tasks/{id}")
//    public Task create(@PathVariable int id, @RequestBody TaskUpdateRequest taskUpdateRequest) {
//        return taskService.create(id, taskUpdateRequest);
//    }
}
