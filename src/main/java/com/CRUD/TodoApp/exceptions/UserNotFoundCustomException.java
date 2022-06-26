package com.CRUD.TodoApp.exceptions;

public class UserNotFoundCustomException extends Exception{

    //creates a custom exception that can be thrown
    public UserNotFoundCustomException(String message){
        super(message);
    }
}
