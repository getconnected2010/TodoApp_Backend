package com.CRUD.TodoApp.service;

import com.CRUD.TodoApp.entity.TodoEntity;
import com.CRUD.TodoApp.repository.TodoRepository;
import org.springframework.beans.BeanUtils;
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

    @Override
    public TodoEntity findById(Long Id) {
        return todoRepository.findById(Id).get();
    }

    @Override
    public String deleteById(Long Id) {
        todoRepository.deleteById(Id);
        return "Successfully deleted";
    }

    @Override
    public int deleteByUsername(String username) {
        return todoRepository.deleteByUsername(username);
    }

    @Override
    public List<TodoEntity> findAllByDescriptionContaining(String description) {
        return todoRepository.findAllByDescriptionContaining(description);
    }

    @Override
    public List<TodoEntity> findAllByCompleted(Boolean bool) {
        return todoRepository.findAllByCompleted(bool);
    }

    //updates a database record, saves it, and returns the updated obj.
    @Override
    public TodoEntity updateTodo(Long Id, TodoEntity todoEntity) {
        TodoEntity dbTodo = todoRepository.findById(Id).get();
        todoEntity.setTodoId(Id);
        BeanUtils.copyProperties(todoEntity, dbTodo);
        todoRepository.save(dbTodo);
        return dbTodo;
    }

    @Override
    public int updateCompletedStatus(Boolean bool, Long Id) {
        return todoRepository.updateCompletedStatus(bool, Id);
    }
}
