package com.CRUD.TodoApp.controller;

import com.CRUD.TodoApp.entity.UserEntity;
import com.CRUD.TodoApp.inputValidations.ValidUsername;
import com.CRUD.TodoApp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    //upload avatar. It receives file and username as form data.
    @PostMapping("/user/avatar")
    public String uploadAvatar(@RequestParam MultipartFile file, ValidUsername username){
        System.out.println(file.getOriginalFilename());
        System.out.println(username.getUsername());
        return file.getOriginalFilename() + "file size " + file.getSize()/1000000.00;
    }
}
