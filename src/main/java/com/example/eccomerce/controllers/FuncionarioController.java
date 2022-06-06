package com.example.eccomerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.FuncionarioDTOModel;
import com.example.eccomerce.models.FuncionarioModel;
import com.example.eccomerce.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
    FuncionarioService service;

    @GetMapping
    public ResponseEntity<FuncionarioModel> getAll(@RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Funcionario");

        return new ResponseEntity<>(service.getAll(token), headers, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioModel> get(@RequestHeader(required = true, name = "Authorization") String token, @PathVariable String cpf) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Funcionario");

        return new ResponseEntity<>(service.get(cpf, token), headers, HttpStatus.ACCEPTED);
    }
    
    @PutMapping()
    public ResponseEntity<Void> put(@Valid @RequestBody FuncionarioDTOModel funcionario, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Atualizado");

        return new ResponseEntity<>(service.put(funcionario, token), headers, HttpStatus.OK);
    }


}
