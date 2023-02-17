package com.interview.task.Wizertask.Service;

import com.interview.task.Wizertask.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void saveCategory(Category category);

    List<Category> getCategory();

    void deleteCategory(Long id);

    Optional<Category> findByID(Long id);
}
