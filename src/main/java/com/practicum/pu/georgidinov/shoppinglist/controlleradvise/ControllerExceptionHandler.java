package com.practicum.pu.georgidinov.shoppinglist.controlleradvise;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.practicum.pu.georgidinov.shoppinglist.entity.Item;
import com.practicum.pu.georgidinov.shoppinglist.exception.ValidationCheckException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Item> handleItemInvalidFormatException(InvalidFormatException exception) {
        log.error("Handling InvalidFormatException");
        log.error(exception.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Item> handleNumberFormatException(NumberFormatException exception) {
        log.error("Handling NumberFormatException");
        log.error(exception.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Item> handleNoSuchElementException(NoSuchElementException exception) {
        log.error("Handling NoSuchElementException");
        log.error(exception.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationCheckException.class)
    public ResponseEntity<Item> handleValidationCheckException(ValidationCheckException exception) {
        log.error("Handling ValidationCheckException");
        log.error(exception.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}