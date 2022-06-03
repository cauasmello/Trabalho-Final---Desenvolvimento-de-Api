package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.GeneralException;
import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Lista de usuarios");

        return new ResponseEntity<>(service.getAll(), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserModel user) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Cadastrar usuario");

        return new ResponseEntity<>(service.create(user), headers, HttpStatus.CREATED);
    }

}
