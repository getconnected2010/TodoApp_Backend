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

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/user/all")
    public List<UserEntity> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/user/avatar/{username}")
    public String getAvatarUrl(@Valid @PathVariable("username") UsernameModel usernameModel) throws UserNotFoundCustomException {
        String username = usernameModel.getUsername();
        return userService.getAvatarUrl(username);
    }

    @PostMapping("/user")
    public String addUser(@RequestBody UserEntity userEntity){
        userService.addUser(userEntity);
        return "User successfully saved to database";
    }

    //upload avatar. It receives file and username as form data.
    @PutMapping("/user/avatar")
    public String updateAvatar(@RequestParam MultipartFile file, UsernameModel username) throws IOException, UserNotFoundCustomException {
        userService.updateAvatar(file, username.getUsername());
        return "???? return type of updateAvatarController?????";
    }
}
