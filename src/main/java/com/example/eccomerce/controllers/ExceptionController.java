package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.GeneralException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> GeneralException(GeneralException exception) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("erro", exception.getMessage());
        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
}
