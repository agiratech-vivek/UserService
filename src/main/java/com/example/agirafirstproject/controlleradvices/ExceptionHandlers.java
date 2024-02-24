package com.example.agirafirstproject.controlleradvices;

import com.example.agirafirstproject.dto.ExceptionDto;
import com.example.agirafirstproject.exceptions.UserDeletedException;
import com.example.agirafirstproject.exceptions.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> userNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(new ExceptionDto(userNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserDeletedException.class)
    public ResponseEntity<ExceptionDto> userDeletedException(UserDeletedException userDeletedException){
        return new ResponseEntity<>(new ExceptionDto(userDeletedException.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> runtimeException(RuntimeException runtimeException){
        return new ResponseEntity<>(new ExceptionDto(runtimeException.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        String methodArgumentNotValidMessage = Arrays.toString(methodArgumentNotValidException.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toArray());
        return new ResponseEntity<>(new ExceptionDto(methodArgumentNotValidMessage), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> exception(Exception exception){
        return new ResponseEntity<>(new ExceptionDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
