package com.CRUD.TodoApp.service;

import com.CRUD.TodoApp.entity.TodoEntity;
import com.CRUD.TodoApp.entity.UserEntity;
import com.CRUD.TodoApp.exceptions.UserNotFoundCustomException;
import com.CRUD.TodoApp.repository.TodoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoServiceInterface{

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserServiceImpl userService;

    @Override
    public List<TodoEntity> getAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public TodoEntity findByUserId(Long Id) {
        return todoRepository.findById(Id).get();
    }

    //finds all tasks for specific username. If username not found, it throws custom exception.
    @Override
    public List<TodoEntity> findByUsername(String username) throws UserNotFoundCustomException {
        List<TodoEntity> usernameTodo = todoRepository.findAllByUsername(username);
        if(usernameTodo.isEmpty()){
            throw new UserNotFoundCustomException("username '" + username + "' doesn't exist in database.");
        }else {
            return usernameTodo;
        }
    }

    @Override
    public void addTodo(TodoEntity todoEntity) throws UserNotFoundCustomException{
        UserEntity userEntity = userService.findUserByUsername(todoEntity.getUsername());
        if(userEntity != null){
            todoRepository.save(todoEntity);
        }else{
            throw new UserNotFoundCustomException("username '"+ todoEntity.getUsername() +"' doesn't exist. Check again or add username first.");
        }
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
    public TodoEntity updateTodo(TodoEntity todoEntity) {
        TodoEntity dbTodo = todoRepository.findById(todoEntity.getTodoId()).get();
        BeanUtils.copyProperties(todoEntity, dbTodo);
        todoRepository.save(dbTodo);
        return dbTodo;
    }

    @Override
    public int updateCompletedStatus(Boolean bool, Long Id) {
        return todoRepository.updateCompletedStatus(bool, Id);
    }
}
