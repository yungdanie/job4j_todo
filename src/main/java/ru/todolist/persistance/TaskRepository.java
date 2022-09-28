package ru.todolist.persistance;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.todolist.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@AllArgsConstructor
@Repository
public class TaskRepository {

    private final GeneralRepository repository;

    public int executeDone(int id) {
        return repository.getResult("update Task set done = :done where id = :id",
                Map.of("done", true, "id", id), Task.class);
    }

    public int delete(int id) {
        return repository.getResult("delete from Task where id = :id", Map.of("id", id), Task.class);
    }

    public void update(Task task) {
        repository.tx((Consumer<Session>) session -> session.merge(task));
    }

    public Optional<Task> findById(int id) {
        return repository.getUniqResult("from Task where id = :id", Map.of("id", id), Task.class);
    }

    public Task add(Task task) {
        repository.tx((Consumer<Session>) session -> session.persist(task));
        return task;
    }

    public List<Task> getAllTask() {
        return repository.getList("from Task", Task.class);
    }

    public List<Task> getByCondDone(boolean cond) {
        return repository.getList("from Task where done = :done", Map.of("done", cond), Task.class);
    }
}
