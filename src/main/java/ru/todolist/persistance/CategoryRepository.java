package ru.todolist.persistance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.todolist.model.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class CategoryRepository {
    private final GeneralRepository repository;

    public List<Category> getAll() {
        return repository.getList("from Category", Category.class);
    }

    public List<Category> getAllByArray(int[] array) {
        Map<String, Integer> integerMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            integerMap.put(String.valueOf(i); );
        }
    }
}
