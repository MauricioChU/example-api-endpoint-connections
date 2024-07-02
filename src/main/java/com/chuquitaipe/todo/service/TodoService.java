package com.chuquitaipe.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuquitaipe.todo.model.Todo;
import com.chuquitaipe.todo.repository.TodoRepository;

@Service
public class TodoService {
    
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodo(){
        return todoRepository.findAll();
    }

    public Todo getTodoById (Long id) {
        return todoRepository.findById(id).orElse(null);
    }
    
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public String deleteTodo(Long id) {
        todoRepository.deleteById(id);
        return "Eliminado correctamente";
    }

    public List<Todo> getCompletedTodos() {
        return todoRepository.findByCompletedTrueOrderByPriorityDesc();
    }

    public List<Todo> getUncompletedTodos() {
        return todoRepository.findByCompletedFalseOrderByPriorityDesc();
    }

    public List<Todo> searchTodosByTitle(String title) {
        return todoRepository.findByTitleContainingIgnoreCase(title);
    }
}
