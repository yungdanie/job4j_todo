package ru.job4j_todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j_todo.model.Task;
import ru.job4j_todo.persistance.TaskHBStore;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskHBStore store;

    public Optional<Integer> executeDone(int id) {
        Optional<Integer> result;
        int res = store.executeDone(id);
        if (res != 0) {
            result = Optional.of(res);
        } else {
            result = Optional.empty();
        }
        return result;
    }
    public Optional<Integer> delete(int id) {
        Optional<Integer> result;
        int res = store.delete(id);
        if (res != 0) {
            result = Optional.of(res);
        } else {
            result = Optional.empty();
        }
        return result;
    }

    public int update(Task task) {
        return store.update(task);
    }

    public Task add(Task task) {
        return store.add(task);
    }

    public Optional<Task> findById(int id) {
        return store.findById(id);
    }
    public List<Task> getAll() {
        return store.getAllTask();
    }

    public List<Task> getDone() {
        return store.getDone();
    }

    public List<Task> getNew() {
        return store.getNew();
    }
}
