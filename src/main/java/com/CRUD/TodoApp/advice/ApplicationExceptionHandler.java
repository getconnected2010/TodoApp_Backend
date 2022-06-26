package com.CRUD.TodoApp.advice;

import com.CRUD.TodoApp.exceptions.UserNotFoundCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    //captures invalid inputs for todoEntity and returns the validation messages in response body
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleInvalidArgument(MethodArgumentNotValidException ex){
        List<String> errorList = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorList.add(error.getDefaultMessage());
        });
        return errorList;
    }

    //custom exception to handle when username don't exist in database
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundCustomException.class)
    public String handleUserNotFoundException(UserNotFoundCustomException ex){
        return ex.getMessage();
    }

    //handles exception when userinput of username is invalid. It iterates and sends them back as response.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<String> handleInvalidUserInput(BindException ex){
        List<String> validationErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            validationErrors.add(error.getDefaultMessage());
        });
        return validationErrors;
    }
}
