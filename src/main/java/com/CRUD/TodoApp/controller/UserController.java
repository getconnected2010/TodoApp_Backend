package com.CRUD.TodoApp.controller;

import com.CRUD.TodoApp.entity.UserEntity;
import com.CRUD.TodoApp.exceptions.UserNotFoundCustomException;
import com.CRUD.TodoApp.model.UsernameModel;
import com.CRUD.TodoApp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

//user api endpoints
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/all")
    public List<UserEntity> getAllUsers(){
        return userService.findAllUsers();
    }

    //gets the avatar url from database
    @GetMapping("/avatar/{username}")
    public String getAvatarUrl(@Valid @PathVariable("username") UsernameModel usernameModel) throws UserNotFoundCustomException {
        String username = usernameModel.getUsername();
        return userService.getAvatarUrl(username);
    }

    @PostMapping("/add")
    public String addUser(@RequestBody UserEntity userEntity){
        userService.addUser(userEntity);
        return "User successfully saved to database";
    }

    //upload avatar. It receives file and username as form data, uploads the image file, then records the s3 url in database.
    @PutMapping("/avatar")
    public String updateAvatar(@RequestParam MultipartFile file, UsernameModel username) throws IOException, UserNotFoundCustomException {
        userService.updateAvatar(file, username.getUsername());
        return "Avatar successfully uploaded.";
    }
}
