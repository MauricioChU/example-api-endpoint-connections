package com.chuquitaipe.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuquitaipe.todo.model.Category;
import com.chuquitaipe.todo.service.CategoryService;

@RestController
@RequestMapping("/mau/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {

        Category category = categoryService.getCategoryById(id);
        if (category != null) {

            return ResponseEntity.ok(category);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with ID: " + id + " does not exist");

        }
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {

        Category newCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {

        Category existingCategory = categoryService.getCategoryById(id);
        if (existingCategory != null) {
            
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().body("Category deleted successfully");

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with ID: " + id + " does not exist");
            
        }
    }
}