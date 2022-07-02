package com.CRUD.TodoApp.repository;

import com.CRUD.TodoApp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserEmail(String email);
    UserEntity findByUsername(String username);

    //native query to update avatar url after s3 upload
    @Transactional
    @Modifying
    @Query(value = "Update todo_app.users set avatar_url = ?1 where username = ?2", nativeQuery = true)
    void updateAvatarUrl(String avatarUrl, String username);
}
