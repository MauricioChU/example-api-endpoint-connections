package com.chuquitaipe.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuquitaipe.todo.model.Category;
import com.chuquitaipe.todo.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Eliminado Correctamente";
    }
}
