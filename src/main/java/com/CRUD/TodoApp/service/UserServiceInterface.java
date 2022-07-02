package com.CRUD.TodoApp.service;

import com.CRUD.TodoApp.entity.UserEntity;
import com.CRUD.TodoApp.exceptions.UserNotFoundCustomException;
import com.CRUD.TodoApp.model.usernameModel;
import org.apache.catalina.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserServiceInterface {
    List<UserEntity> findAllUsers();
    UserEntity findUserById(Long Id);
    UserEntity findUserByEmail(String email);
    UserEntity findUserByUsername(String username);

    String addUser(UserEntity userEntity);

    String deleteUser(Long Id);

    String updateUser(UserEntity userEntity, Long Id);

    String updateAvatar(MultipartFile file, String username) throws IOException, UserNotFoundCustomException;
}
