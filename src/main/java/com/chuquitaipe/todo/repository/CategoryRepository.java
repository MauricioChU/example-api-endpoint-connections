package com.chuquitaipe.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chuquitaipe.todo.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
