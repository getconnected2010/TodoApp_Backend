package com.CRUD.TodoApp.repository;

import com.CRUD.TodoApp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserEmail(String email);
    UserEntity findByUsername(String username);
}
