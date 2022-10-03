package ru.todolist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.todolist.model.Priority;
import ru.todolist.persistance.PriorityRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PriorityService {

    private final PriorityRepository store;

    public List<Priority> getAll() {
        return store.getAll();
    }
}
