package com.CRUD.TodoApp.advice;

import com.CRUD.TodoApp.exceptions.UserNotFoundCustomException;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundCustomException.class)
    public String handleUserNotFoundException(UserNotFoundCustomException ex){
        return ex.getMessage();
    }
}
