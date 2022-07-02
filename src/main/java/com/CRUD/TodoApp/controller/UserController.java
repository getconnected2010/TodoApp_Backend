package com.CRUD.TodoApp.controller;

import com.CRUD.TodoApp.entity.UserEntity;
import com.CRUD.TodoApp.exceptions.UserNotFoundCustomException;
import com.CRUD.TodoApp.model.usernameModel;
import com.CRUD.TodoApp.service.UserServiceImpl;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.CRUD.TodoApp.utils.FIleUtils.convertMultipartToFile;

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
    @PutMapping("/user/avatar")
    public String updateAvatar(@RequestParam MultipartFile file, usernameModel username) throws IOException, UserNotFoundCustomException {
        userService.updateAvatar(file, username.getUsername());
        return "???? return type of updateAvatarController?????";
    }
}
