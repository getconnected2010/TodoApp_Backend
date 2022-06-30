package com.CRUD.TodoApp.controller;

import com.CRUD.TodoApp.entity.UserEntity;
import com.CRUD.TodoApp.model.usernameModel;
import com.CRUD.TodoApp.service.UserServiceImpl;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private AmazonS3 amazonS3;
    @Value("${aws.bucketName}")
    private String bucket;

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
    public String uploadAvatar(@RequestParam MultipartFile file, usernameModel username) throws IOException {

        //changes the incoming Multipart type file to File type file.
        File uploadFile = convertMultipartToFile(file);
        //s3 upload
        amazonS3.putObject(bucket, file.getOriginalFilename(), uploadFile);
        return file.getOriginalFilename() + "file size " + file.getSize()/1000000.00;
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(multipartFile.getBytes());
        return convertedFile;
    }
}
