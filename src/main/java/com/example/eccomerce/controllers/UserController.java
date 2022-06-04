package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.GeneralException;
import com.example.eccomerce.models.ClienteDTOModel;
import com.example.eccomerce.models.FuncionarioDTOModel;
import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllCliente() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Lista de usuarios");

        return new ResponseEntity<>(service.getAllCliente(), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<String> createCliente(@Valid @RequestBody ClienteDTOModel user) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Cadastrar usuario");

        return new ResponseEntity<>(service.createCliente(user), headers, HttpStatus.CREATED);
    }

    @GetMapping("/funcionario")
    public ResponseEntity<List<UserModel>> getAllFuncionario() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Lista de usuarios");

        return new ResponseEntity<>(service.getAllFuncionario(), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping("/funcionario")
    public ResponseEntity<String> createFuncionario(@Valid @RequestBody FuncionarioDTOModel user) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Cadastrar usuario");

        return new ResponseEntity<>(service.createFuncionario(user), headers, HttpStatus.CREATED);
    }

    @PostMapping("/logar")
    public ResponseEntity<String> login(@Valid @RequestBody UserModel user) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Cadastrar usuario");

        return new ResponseEntity<>(service.login(user), headers, HttpStatus.CREATED);
    }

}
