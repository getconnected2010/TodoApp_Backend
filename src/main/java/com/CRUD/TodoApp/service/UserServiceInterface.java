package com.CRUD.TodoApp.service;

import com.CRUD.TodoApp.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;

public interface UserServiceInterface {
    List<UserEntity> findAllUsers();
    UserEntity findUserById(Long Id);
    UserEntity findUserByEmail(String email);
    UserEntity findUserByUsername(String username);

    String addUser(UserEntity userEntity);

    String deleteUser(Long Id);

    String updateUser(UserEntity userEntity, Long Id);
}
