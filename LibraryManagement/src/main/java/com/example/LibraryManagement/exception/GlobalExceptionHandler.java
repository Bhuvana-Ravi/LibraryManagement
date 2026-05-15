package com.example.LibraryManagement.exception;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public Map<String, String> handleBookNotFound(BookNotFoundException ex){
        Map<String, String> error=new HashMap<>();

        error.put("message", ex.getMessage());
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String >
    handleValidation(
            MethodArgumentNotValidException ex
    ){
        Map<String, String> errors=new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error->{
            errors.put(
                    error.getField(),error.getDefaultMessage()
            );
        });
        return errors;
    }
}
