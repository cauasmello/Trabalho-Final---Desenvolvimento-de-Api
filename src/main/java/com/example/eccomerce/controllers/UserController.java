package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.ErrorException;
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
        headers.add("Success", "Lista de clientes");

        return new ResponseEntity<>(service.getAllCliente(), headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getClienteById(@PathVariable Integer id) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Cliente");

        return new ResponseEntity<>(service.getClienteById(id), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Void> createCliente(@Valid @RequestBody ClienteDTOModel user) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Criado com sucesso!");

        return new ResponseEntity<>(service.createCliente(user), headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCliente(@Valid @RequestBody UserModel user, @PathVariable Integer id) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Atualizado");

        return new ResponseEntity<>(service.updateCliente(user, id), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Deletado");

        return new ResponseEntity<>(service.deleteCliente(id), headers, HttpStatus.OK);
    }

    @GetMapping("/funcionario")
    public ResponseEntity<List<UserModel>> getAllFuncionario() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Lista de funcionarios");

        return new ResponseEntity<>(service.getAllFuncionario(), headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<UserModel> getFuncionarioById(@PathVariable Integer id) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Funcionario");

        return new ResponseEntity<>(service.getFuncionarioById(id), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping("/funcionario")
    public ResponseEntity<Void> createFuncionario(@Valid @RequestBody FuncionarioDTOModel user) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Criado com sucesso!");

        return new ResponseEntity<>(service.createFuncionario(user), headers, HttpStatus.CREATED);
    }

    @PutMapping("/funcionario/{id}")
    public ResponseEntity<Void> updateFuncionario(@Valid @RequestBody UserModel user, @PathVariable Integer id) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Atualizado");

        return new ResponseEntity<>(service.updateFuncionario(user, id), headers, HttpStatus.OK);
    }

    @DeleteMapping("/funcionario/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Integer id) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Deletado");

        return new ResponseEntity<>(service.deleteFuncionario(id), headers, HttpStatus.OK);
    }

}
