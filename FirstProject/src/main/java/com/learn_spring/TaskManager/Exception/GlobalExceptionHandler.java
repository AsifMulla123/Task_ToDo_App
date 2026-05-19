package com.learn_spring.TaskManager.Exception;

import com.learn_spring.TaskManager.DTO.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleTaskNotFoundException(TaskNotFoundException ex) {

        ErrorResponseDto error = new ErrorResponseDto(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().
                getFieldErrors().
                forEach(error -> errors.put(error.getField(), error.getDefaultMessage())
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }

    //Handling Parsing exception
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResponseDto error = new ErrorResponseDto(
                "Invalid Request Body",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex) {
        ErrorResponseDto error = new ErrorResponseDto( //Need logging here need to update the code letter
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        return  new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
