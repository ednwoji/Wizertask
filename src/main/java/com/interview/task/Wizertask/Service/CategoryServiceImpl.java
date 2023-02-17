package com.interview.task.Wizertask.Service;

import com.interview.task.Wizertask.Entity.Category;
import com.interview.task.Wizertask.Repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category category) {
        log.info("Saving Category");
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findByID(Long id) {
        return categoryRepository.findById(id);
    }
}
