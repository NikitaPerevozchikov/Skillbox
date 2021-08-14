package main.model;

import main.EntityNotFoundException;
import main.model.Task;
import main.model.TaskRepository;
import main.model.TaskUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public int add(Task task) {
        return taskRepository.save(task).getId();
    }

    public Task get(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return optionalTask.get();
    }

    public List<Task> getAllTask() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    public void delete(int id) {
        if (taskRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException();
        }
        taskRepository.deleteById(id);
    }

    public void deleteAllTask() {
        taskRepository.deleteAll();
    }

    public Task create(int id, TaskUpdateRequest taskUpdateRequest) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Task task = optionalTask.get();
        if (taskUpdateRequest.getName() != null) {
            task.setName(taskUpdateRequest.getName());
        }
        if (taskUpdateRequest.getText() != null) {
            task.setText(taskUpdateRequest.getText());
        }
        if (taskUpdateRequest.getDeadline() != null) {
            task.setDeadline(taskUpdateRequest.getDeadline());
        }
        return taskRepository.save(task);
    }
}
