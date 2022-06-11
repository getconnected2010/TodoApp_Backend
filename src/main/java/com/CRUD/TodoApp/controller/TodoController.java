package com.CRUD.TodoApp.controller;

import com.CRUD.TodoApp.entity.TodoEntity;
import com.CRUD.TodoApp.service.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    TodoServiceImpl todoService;

    @GetMapping("/todo/all")
    public List<TodoEntity> getAllTodo(){
        return todoService.getAllTodo();
    }

    @GetMapping("/todo/username/{username}")
    public List<TodoEntity> getTodoByUsername(@PathVariable("username") String username){
        return todoService.findByUsername(username);
    }

    @PostMapping("/todo/add")
    public String addTodo(@RequestBody TodoEntity todoEntity){
        todoService.addTodo(todoEntity);
        return "Task successfully added to the list";
    }
}
