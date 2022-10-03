package ru.todolist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.todolist.model.Category;
import ru.todolist.persistance.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository store;

    public List<Category> getAll() {
        return store.getAll();
    }

    public List<Category> getAllByArray(int[] array) {

    }
}
