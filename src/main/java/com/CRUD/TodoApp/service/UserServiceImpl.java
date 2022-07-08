package com.CRUD.TodoApp.service;

import com.CRUD.TodoApp.entity.UserEntity;
import com.CRUD.TodoApp.exceptions.UserNotFoundCustomException;
import com.CRUD.TodoApp.repository.UserRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.CRUD.TodoApp.utils.FIleUtils.convertMultipartToFile;

@Service
public class UserServiceImpl implements UserServiceInterface{

    @Autowired
    UserRepository userRepository;
    //for password operations.
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AmazonS3 amazonS3;
    //pulls bucket name from secret.properties file.
    @Value("${aws.bucketName}")
    private String bucket;

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
        //encrypts the incoming plain password from user.
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        //sets user role default as 'user'.
        if(userEntity.getRole() == null){
            userEntity.setRole("USER");
        }
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

    @Override
    public String updateAvatar(MultipartFile file, String username) throws IOException, UserNotFoundCustomException {
        UserEntity userEntity= userRepository.findByUsername(username);
        if(userEntity == null){
            throw new  UserNotFoundCustomException("Unknown username. Please check and try again.");
        }else {
            //changes the incoming Multipart type file to File type file.
            File uploadFile = convertMultipartToFile(file);
            UUID uuid = UUID.randomUUID();
            String fileName = username+"."+uuid;
            //s3 upload
            PutObjectResult result = amazonS3.putObject(bucket, fileName, uploadFile);
            String avatarUrl = "https://todo-list-spring-boot.s3.amazonaws.com/"+fileName;
            uploadFile.delete();
            userRepository.updateAvatarUrl(avatarUrl, username);
            return null;
        }
    }

    @Override
    public String getAvatarUrl(String username) throws UserNotFoundCustomException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null){
            throw new UserNotFoundCustomException("Username not found. Please check again.");
        }else {
            return userEntity.getAvatarUrl();
        }
    }

    @Override
    public void updatePassword(String password, String username) throws UserNotFoundCustomException {
        String newPassword = passwordEncoder.encode(password);
        userRepository.updatePassword(newPassword, username);
    }
}
