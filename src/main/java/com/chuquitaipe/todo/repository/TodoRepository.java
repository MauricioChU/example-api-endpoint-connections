package com.chuquitaipe.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chuquitaipe.todo.model.Todo;

public interface TodoRepository extends JpaRepository <Todo, Long>{
    List<Todo> findByCompletedTrueOrderByPriorityDesc();
    List<Todo> findByCompletedFalseOrderByPriorityDesc();
    List<Todo> findByTitleContainingIgnoreCase(String title);
} 
