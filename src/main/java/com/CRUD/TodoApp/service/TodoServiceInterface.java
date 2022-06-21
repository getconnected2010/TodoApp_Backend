package com.CRUD.TodoApp.service;

import com.CRUD.TodoApp.entity.TodoEntity;

import java.util.List;

public interface TodoServiceInterface {

    List<TodoEntity> getAllTodo();

    TodoEntity findByUserId(Long Id);

    List<TodoEntity> findByUsername(String username);

    void addTodo(TodoEntity todoEntity);

    TodoEntity findById(Long Id);

    String deleteById(Long Id);

    int deleteByUsername(String username);

    List<TodoEntity> findAllByDescriptionContaining(String description);

    List<TodoEntity> findAllByCompleted(Boolean bool);

    TodoEntity updateTodo(Long Id, TodoEntity todoEntity);

    //updates completion status using ID.
    int updateCompletedStatus(Boolean bool, Long Id);
}
