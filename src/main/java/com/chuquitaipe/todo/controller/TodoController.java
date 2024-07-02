package com.chuquitaipe.todo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuquitaipe.todo.model.Todo;
import com.chuquitaipe.todo.service.TodoService;

@RestController
@RequestMapping("/mau/todo")
public class TodoController {
    
    @Autowired
    private TodoService todoService;

    @GetMapping
    private List<Todo> getAllTodo(){
        return todoService.getAllTodo();
    }

    @GetMapping("/{id}")
    private Todo getTodoById(@PathVariable Long id){
        return todoService.getTodoById(id);
    }

    @PostMapping
    private ResponseEntity<Todo> saveTodo(@RequestBody Todo todo){
        
        Todo newTodo = todoService.saveTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteTodo(@PathVariable Long id){

        Todo existingTodo = todoService.getTodoById(id);

        if (existingTodo != null) {
            todoService.deleteTodo(id);
            return ResponseEntity.ok().body("Eliminado Correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TODO con ID: " + id + "No Existe");
        }
    }

    @PutMapping("/{id}/updateCompleted")
    private ResponseEntity<?> updateCompleted (@PathVariable Long id, @RequestBody Map<String, Boolean> request){

      Todo existingTodo = todoService.getTodoById(id);
      Boolean completedUpdate = request.get("completed");

      if (existingTodo != null){

        existingTodo.setCompleted(completedUpdate);
        todoService.saveTodo(existingTodo);
        return ResponseEntity.ok(existingTodo);

      } else {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TODO con ID: " + id + "No existe");

      }
    }

    @GetMapping("/completed")
    private List<Todo> getCompletedTodos() {
        return todoService.getCompletedTodos();
    }

    @GetMapping("/uncompleted")
    private List<Todo> getUncompletedTodos() {
        return todoService.getUncompletedTodos();
    }

    @GetMapping("/search")
    private ResponseEntity<?> searchTodosByTitle(@RequestParam String query) {
        try {

            List<Todo> todos = todoService.searchTodosByTitle(query);

            if (todos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron TODOs con el título: " + query);
            }

            return ResponseEntity.ok(todos);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error: " + e.getMessage());
        
        }
    }

}
