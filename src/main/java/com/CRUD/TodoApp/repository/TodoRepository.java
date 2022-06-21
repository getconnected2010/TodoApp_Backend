package com.CRUD.TodoApp.repository;

import com.CRUD.TodoApp.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findAllByUsername(String username);

    List<TodoEntity> findAllByDescriptionContaining(String description);

    List<TodoEntity> findAllByCompleted(Boolean bool);

    //modified delete statement
    @Transactional
    @Modifying
    @Query(value = "delete from todo_app.todo_list where username = ?1", nativeQuery = true)
    int deleteByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "update todo_app.todo_list set completed = ?1 where todo_id = ?2", nativeQuery = true)
    int updateCompletedStatus(Boolean bool, Long Id);
}
