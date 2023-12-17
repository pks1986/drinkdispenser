package com.example.drinkdispenser.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.drinkdispenser.exception.DrinkDispenserException;

@ControllerAdvice
public class DrinkDispenserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DrinkDispenserException.class)
    public ResponseEntity<String> handleDrinkDispenserException(DrinkDispenserException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
    }
}

