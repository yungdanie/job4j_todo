package ru.todolist.persistance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.todolist.model.Priority;

import java.util.List;

@AllArgsConstructor
@Repository
public class PriorityRepository {
    private final GeneralRepository repository;

    public List<Priority> getAll() {
        return repository.getList("from Priority", Priority.class);
    }
}
