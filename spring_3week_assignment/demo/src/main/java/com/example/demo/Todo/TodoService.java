package com.example.demo.Todo;

import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    TodoRepository todoRepository;
    UserRepository userRepository;
    TodoService(TodoRepository todoRepository,UserRepository userRepository){
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public Todo createTodo(Todo todo, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        todo.setUser(user);

        return todoRepository.save(todo);
    }

    public void deleteTodo(Long todoId){
        if(todoRepository.existsById(todoId)){
            todoRepository.deleteById(todoId);
        }
        else {
            throw new RuntimeException("Todo not found");
        }
    }

    public List<Todo> getTodo(Long userId){
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return todoRepository.findByUser_Id(userId);
    }

    public Todo updateTodo(Todo todo,Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Todo 존재 + 해당 유저의 것인지 확인
        Todo updateTodo = todoRepository.findById(todo.getId())
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (!updateTodo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized update: Not your Todo");
        }

        updateTodo.setTitle(todo.getTitle());
        updateTodo.setTodo(todo.getTodo());

        return updateTodo;
    }
}
