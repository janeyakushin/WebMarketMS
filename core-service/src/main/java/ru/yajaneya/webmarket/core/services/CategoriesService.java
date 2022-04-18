package ru.yajaneya.webmarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yajaneya.webmarket.core.entities.Category;
import ru.yajaneya.webmarket.core.repositories.CategoriesRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public Optional<Category> findByCategoryName (String name) {
        return categoriesRepository.findByCategoryName(name);
    }
}
