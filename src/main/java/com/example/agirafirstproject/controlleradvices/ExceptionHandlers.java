package com.example.agirafirstproject.controlleradvices;

import com.example.agirafirstproject.dto.ExceptionDto;
import com.example.agirafirstproject.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> userNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(new ExceptionDto(userNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> runtimeException(RuntimeException runtimeException){
        return new ResponseEntity<>(new ExceptionDto(runtimeException.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
//        methodArgumentNotValidException.getBindingResult().getAllErrors().stream().filter(objectError -> objectError.equals())
        return new ResponseEntity<>(new ExceptionDto(methodArgumentNotValidException.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> exception(Exception exception){
        return new ResponseEntity<>(new ExceptionDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
