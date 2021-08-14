package essence;

import main.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    private static AtomicInteger currentId = new AtomicInteger(1);
    private static ConcurrentHashMap<Integer, Task> listTask = new ConcurrentHashMap<>();

    public static int addTask(Task task) {
        int id = currentId.getAndIncrement();
        listTask.put(id, task);
        return id;
    }

    public static void deleteTask(int id) {
        listTask.computeIfPresent(id, (a, b) -> listTask.remove(a));
    }

    public static void deleteAllTask() {
        listTask.clear();
    }

    public static Task getTask(int id) {
        return listTask.get(id);
    }

    public static List<Task> getAllTask() {
        return new ArrayList<>(listTask.values());
    }
}

