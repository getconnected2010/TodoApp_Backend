package com.CRUD.TodoApp.service;

import com.CRUD.TodoApp.entity.TodoEntity;
import com.CRUD.TodoApp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoServiceInterface{

    @Autowired
    TodoRepository todoRepository;

    @Override
    public List<TodoEntity> getAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public TodoEntity findByUserId(Long Id) {
        return todoRepository.findById(Id).get();
    }

    @Override
    public List<TodoEntity> findByUsername(String username) {
        return todoRepository.findAllByUsername(username);
    }

    @Override
    public void addTodo(TodoEntity todoEntity) {
        todoRepository.save(todoEntity);
    }
}
