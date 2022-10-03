package ru.todolist.persistance;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.todolist.model.Category;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Repository
public class CategoryRepository {
    private final GeneralRepository repository;

    public List<Category> getAll() {
        return repository.getList("from Category", Category.class);
    }

    public List<Category> getAllById(int[] array) {
        return repository.tx((Function<Session, List<Category>>) session ->
                session.createQuery("from Category where id in :array", Category.class)
                .setParameterList("array", Arrays.stream(array).boxed().toList())
                .list());
    }
}
