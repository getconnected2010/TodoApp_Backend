package com.CRUD.TodoApp.controller;

import com.CRUD.TodoApp.entity.TodoEntity;
import com.CRUD.TodoApp.service.TodoServiceImpl;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.Console;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class TodoController {

    @Autowired
    TodoServiceImpl todoService;

    @GetMapping("/todo/all")
    public List<TodoEntity> getAllTodo(){
        System.out.println(todoService.getAllTodo());
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

    @DeleteMapping("/todo/delete/{id}")
    public TodoEntity deleteTodo(@PathVariable("id") Long todoId){
        TodoEntity deletedEntity = todoService.findById(todoId);
        todoService.deleteById(todoId);
        return deletedEntity;
    }
}
