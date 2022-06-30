package com.CRUD.TodoApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class usernameModel {

    @Size(min = 3, max = 12, message = "username should be minimum 3 and maximum 12 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only letters and numbers allowed as username.")
    private String username;
}
