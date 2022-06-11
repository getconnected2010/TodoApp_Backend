package com.CRUD.TodoApp.service;

import com.CRUD.TodoApp.entity.UserEntity;
import com.CRUD.TodoApp.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findUserById(Long Id) {
        return userRepository.findById(Id).get();
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String addUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        return "User Successfully added";
    }

    @Override
    public String deleteUser(Long Id) {
        userRepository.deleteById(Id);
        return "User successfully deleted";
    }

    @Override
    public String updateUser(UserEntity userEntity, Long Id) {
        UserEntity DBUser = userRepository.findById(Id).get();
        BeanUtils.copyProperties(userEntity, DBUser);
        userRepository.save(DBUser);
        return "User successfully updated";
    }
}
