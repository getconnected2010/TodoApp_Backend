package com.CRUD.TodoApp.service;

import com.CRUD.TodoApp.entity.TodoEntity;
import com.CRUD.TodoApp.exceptions.UserNotFoundCustomException;

import java.util.List;

public interface TodoServiceInterface {

    List<TodoEntity> getAllTodo();

    TodoEntity findByUserId(Long Id);

    List<TodoEntity> findByUsername(String username) throws UserNotFoundCustomException;

    void addTodo(TodoEntity todoEntity);

    TodoEntity findById(Long Id);

    String deleteById(Long Id);

    int deleteByUsername(String username);

    List<TodoEntity> findAllByDescriptionContaining(String description);

    List<TodoEntity> findAllByCompleted(Boolean bool);

    TodoEntity updateTodo(TodoEntity todoEntity);

    //updates completion status using ID.
    int updateCompletedStatus(Boolean bool, Long Id);
}
