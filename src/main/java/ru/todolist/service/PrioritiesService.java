package ru.todolist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.todolist.model.Priorities;
import ru.todolist.persistance.PrioritiesRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PrioritiesService {

    private final PrioritiesRepository repository;

    public List<Priorities> getAll() {
        return repository.getAll();
    }
}
