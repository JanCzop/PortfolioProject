package com.example.portfolioproject.Exceptions;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.logging.Logger;

@ControllerAdvice
public class Global_exception_handler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle_illegal_argument_exception(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exc_entity_not_found.class)
    public ResponseEntity<String> handle_entity_not_found_exception(Exc_entity_not_found e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exc_entity_already_exist.class)
    public ResponseEntity<String> handle_entity_already_exists(Exc_entity_already_exist ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle_global_exception(Exception ex, WebRequest request){
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exc_null_data.class)
    public ResponseEntity<String> handle_null_data(Exc_null_data ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
    }
}

