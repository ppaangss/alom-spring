package com.example.demo.Todo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @PostMapping("/{userId}")
    public Todo createTodo(@PathVariable Long userId, @RequestBody Todo todo){
        return todoService.createTodo(todo,userId);
    }

    @GetMapping("/{userId}")
    public List<Todo> getTodo(@PathVariable Long userId){
        return todoService.getTodo(userId);
    }

    @PutMapping("/{userId}")
    public Todo updateTodo(@PathVariable Long userId,@RequestBody Todo todo){
        return todoService.updateTodo(todo,userId);
    }

    @DeleteMapping("/{todoId}")
    public String deleteTodo(@PathVariable Long todoId){
        todoService.deleteTodo(todoId);
        return "Deleted";
    }

}
