package com.CRUD.TodoApp.controller;

import com.CRUD.TodoApp.entity.TodoEntity;
import com.CRUD.TodoApp.exceptions.UserNotFoundCustomException;
import com.CRUD.TodoApp.model.UsernameModel;
import com.CRUD.TodoApp.service.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    TodoServiceImpl todoService;

    @GetMapping("/todo/all")
    @Validated
    public List<TodoEntity> getAllTodo(){
        System.out.println(todoService.getAllTodo());
        return todoService.getAllTodo();
    }

    //fetches all tasks associated to specific username
    @GetMapping("/todo/username/{username}")
    public List<TodoEntity> getTodoByUsername(@Valid UsernameModel usernameModel) throws UserNotFoundCustomException {
        return todoService.findByUsername(usernameModel.getUsername());
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

    //adds to the todolist
    @PostMapping("/todo/add")
    public String addTodo(@Valid @RequestBody TodoEntity todoEntity) throws UserNotFoundCustomException{
        todoService.addTodo(todoEntity);
        return "Task successfully added to the list";
    }

//    //uploads multiple files
//    @PostMapping("/todo/avatar")
//    public String uploadAvatar(@RequestParam MultipartFile[] files) throws IOException {
//        for (MultipartFile file: files) {
//            System.out.println(file.getBytes());
//            System.out.println(file.getContentType());
//            System.out.println(file.getOriginalFilename());
//            System.out.println(file.getSize() / 1000000.00);
//            System.out.println(file.getResource());
//        };
//        return "file uploaded";
//    }

    //takes in Id in param and todoList obj in body and updates database obj.
    @PutMapping("/todo/update")
    public TodoEntity updateTodo(@Valid @RequestBody TodoEntity todoEntity){
        return todoService.updateTodo(todoEntity);
    }

    //takes completion status and Id and updates database
    @PutMapping("/todo/update/completion/{bool}/{id}")
    public int updateCompletedStatus(@PathVariable("bool") Boolean bool, @PathVariable("id") Long Id){
        return todoService.updateCompletedStatus(bool, Id);
    }

    //deletes a todoList list using id.
    @DeleteMapping("/todo/delete/id/{id}")
    public TodoEntity deleteTodo(@PathVariable("id") Long todoId){
        TodoEntity deletedEntity = todoService.findById(todoId);
        todoService.deleteById(todoId);
        return deletedEntity;
    }

    //deletes all items in todolist with a username
    @DeleteMapping("/todo/delete/username/{username}")
    public int deleteByUsername(@PathVariable("username") String username){
        return todoService.deleteByUsername(username);
    }

}
