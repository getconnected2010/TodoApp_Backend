package com.CRUD.TodoApp.service;

import com.CRUD.TodoApp.entity.TodoEntity;

import java.util.List;

public interface TodoServiceInterface {

    List<TodoEntity> getAllTodo();

    TodoEntity findByUserId(Long Id);

    List<TodoEntity> findByUsername(String username);

    void addTodo(TodoEntity todoEntity);
}
