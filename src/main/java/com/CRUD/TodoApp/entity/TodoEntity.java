package com.CRUD.TodoApp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todo_list")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long todoId;
    @NotBlank(message = "Title field cannot be empty.")
    private String title;
    private String description;
    private java.sql.Date dueDate;
    private boolean completed;
    private String username;
}
