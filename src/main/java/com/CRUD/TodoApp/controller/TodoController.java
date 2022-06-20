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

    //fetches all tasks associated to specific username
    @GetMapping("/todo/username/{username}")
    public List<TodoEntity> getTodoByUsername(@PathVariable("username") String username){
        return todoService.findByUsername(username);
    }

    //fetches all tasks that have any matching word in their description.
    @GetMapping("/todo/description/{description}")
    public List<TodoEntity> findByDescription(@PathVariable("description") String description){
    return todoService.findAllByDescriptionContaining(description);
    }

    //fetches all tasks that are either completed or not completed depending on the bool value in param.
    @GetMapping("/todo/completed/{bool}")
    public List<TodoEntity> findAllByCompleted(@PathVariable("bool") boolean bool){
        return todoService.findAllByCompleted(bool);
    }

    //adds to the todo list
    @PostMapping("/todo/add")
    public String addTodo(@RequestBody TodoEntity todoEntity){
        todoService.addTodo(todoEntity);
        return "Task successfully added to the list";
    }

    //deletes a todo list using id.
    @DeleteMapping("/todo/delete/{id}")
    public TodoEntity deleteTodo(@PathVariable("id") Long todoId){
        TodoEntity deletedEntity = todoService.findById(todoId);
        todoService.deleteById(todoId);
        return deletedEntity;
    }
}
