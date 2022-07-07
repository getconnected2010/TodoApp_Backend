package com.CRUD.TodoApp.controller;

import com.CRUD.TodoApp.entity.TodoEntity;
import com.CRUD.TodoApp.exceptions.UserNotFoundCustomException;
import com.CRUD.TodoApp.model.UsernameModel;
import com.CRUD.TodoApp.service.TodoServiceImpl;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//'todo' api endpoints
@RestController
@RequestMapping("/todo")
@SecurityRequirement(name = "TodoAppApi")
public class TodoController {

    @Autowired
    TodoServiceImpl todoService;

    @GetMapping("/all")
//    @ApiOperation(value = "Gets all todo tasks.", notes = "Returns all the tasks from the database.")
    public List<TodoEntity> getAllTodo(){
        System.out.println(todoService.getAllTodo());
        return todoService.getAllTodo();
    }

    //fetches all tasks associated to specific username
    @GetMapping("/username/{username}")
//    @ApiOperation(value = "Get all tasks for a username", notes = "Retrieves all tasks from db using username input if it exists in db.")
    public List<TodoEntity> getTodoByUsername(@Valid UsernameModel usernameModel) throws UserNotFoundCustomException {
        return todoService.findByUsername(usernameModel.getUsername());
    }

    //fetches all tasks that have any matching word in their description.
    @GetMapping("/description/{description}")
//    @ApiOperation(value = "Get a task by description", notes = "Retrieves a task from db by matching search word input.")
    public List<TodoEntity> findByDescription(@PathVariable("description") String description){
    return todoService.findAllByDescriptionContaining(description);
    }

    //fetches all tasks that are either completed or not completed depending on the bool value in param.
    @GetMapping("/completed/{bool}")
//    @ApiOperation(value = "Find tasks by completion", notes = "retrieves a list of tasks depending on the input of completion status.")
    public List<TodoEntity> findAllByCompleted(@PathVariable("bool") boolean bool){
        return todoService.findAllByCompleted(bool);
    }

    //adds to the todolist
    @PostMapping("/add")
//    @ApiOperation(value = "Add a todo task", notes = "Adds a todo task to the database.")
    public String addTodo(@Valid @RequestBody TodoEntity todoEntity) throws UserNotFoundCustomException{
        todoService.addTodo(todoEntity);
        return "Task successfully added to the list";
    }

    //takes in Id in param and todoList obj in body and updates database obj.
    @PutMapping("/update")
//    @ApiOperation(value = "update a todo task", notes = "Updates an existing todo task based on new inputs.")
    public TodoEntity updateTodo(@Valid @RequestBody TodoEntity todoEntity){
        return todoService.updateTodo(todoEntity);
    }

    //takes completion status and Id and updates database
    @PutMapping("/update/completion/{bool}/{id}")
//    @ApiOperation(value = "Update completion of task using ID", notes = "Updates the completion of a task to either true or false by using the task ID as input.")
    public int updateCompletedStatus(@PathVariable("bool") Boolean bool, @PathVariable("id") Long Id){
        return todoService.updateCompletedStatus(bool, Id);
    }

    //deletes a todoList list using id.
    @DeleteMapping("/delete/id/{id}")
//    @ApiOperation(value = "Delete a todo task using ID.", notes = "Deletes a todo task from database using user ID as input.")
    public TodoEntity deleteTodo(@PathVariable("id") Long todoId){
        TodoEntity deletedEntity = todoService.findById(todoId);
        todoService.deleteById(todoId);
        return deletedEntity;
    }

    //deletes all items in todolist with a username
    @DeleteMapping("/delete/username/{username}")
//    @ApiOperation(value = "Delete all tasks for a username", notes = "Deletes all tasks for a specific username from database using username as input.")
    public String deleteByUsername(@Valid UsernameModel usernameModel) {
        int rowsAffected = todoService.deleteByUsername(usernameModel.getUsername());
        return rowsAffected + " records were deleted.";
    }
}
