package com.CRUD.TodoApp.controller;

import com.CRUD.TodoApp.entity.UserEntity;
import com.CRUD.TodoApp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/user/all")
    public List<UserEntity> getAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping("/user")
    public String addUser(@RequestBody UserEntity userEntity){
        userService.addUser(userEntity);
        return "User successfully saved to database";
    }


}
