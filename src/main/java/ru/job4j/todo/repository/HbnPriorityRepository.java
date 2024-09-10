package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class HbnPriorityRepository implements PriorityRepository {

    private final CrudRepository crudRepository;

    @Override
    public List<Priority> findAll() {
        return crudRepository.query(
                "from Priority",
                Priority.class);
    }

    @Override
    public Optional<Priority> getPriorityByName(String name) {
        return crudRepository.optional(
                "from Priority where name = :fName",
                Priority.class,
                Map.of("fName", name));
    }

}
