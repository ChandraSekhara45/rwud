package com.example.rwud.controller;


import com.example.rwud.entity.Todo;
import com.example.rwud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    //localhost:8080/todos
    @GetMapping("/todos")
    public List<Todo> getTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos ;
    }

    @GetMapping("/todos/{id}")
    public Todo getTodo(@PathVariable Integer id) {
        return todoRepository.findById(id).get();
    }

    @PostMapping("/todos/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createTodo(@RequestBody Todo todo) {
        todoRepository.save(todo) ;
    }

    @PutMapping("/todos/update/{id}")
    public Todo updateTodo(@RequestBody Todo todo, @PathVariable Integer id) {
        Todo existingTodo = todoRepository.findById(id).get();
        existingTodo.setId(id);
        return todoRepository.save(existingTodo);
    }

    @DeleteMapping("/todos/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Integer id) {
        todoRepository.deleteById(id);
    }

    @DeleteMapping("todos/delete/all")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }

}
