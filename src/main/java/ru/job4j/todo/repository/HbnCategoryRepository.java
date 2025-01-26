package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.job4j.todo.model.Category;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class HbnCategoryRepository implements CategoryRepository {

    private final CrudRepository crudRepository;

    @Override
    public List<Category> findAll() {
        return crudRepository.query(
                "from Category",
                Category.class);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return crudRepository.optional(
                "from Category where name = :fName",
                Category.class,
                Map.of("fName", name));
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        return crudRepository.optional(
                "from Category where id = :fId",
                Category.class,
                Map.of("fId", id)
        );
    }
}
