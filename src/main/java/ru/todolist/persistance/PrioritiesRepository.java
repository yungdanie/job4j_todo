package ru.todolist.persistance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.todolist.model.Priorities;

import java.util.List;

@AllArgsConstructor
@Repository
public class PrioritiesRepository {
    private final GeneralRepository repository;

    public List<Priorities> getAll() {
        return repository.getList("from Priorities", Priorities.class);
    }
}
