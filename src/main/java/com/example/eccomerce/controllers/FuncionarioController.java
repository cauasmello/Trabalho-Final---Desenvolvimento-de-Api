package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.FuncionarioDTOModel;
import com.example.eccomerce.models.FuncionarioModel;
import com.example.eccomerce.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
    FuncionarioService service;

    @GetMapping
    public ResponseEntity<FuncionarioModel> getMyData(@RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Funcionario");

        return new ResponseEntity<>(service.getMyData(token), headers, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioModel> get(@RequestHeader(required = true, name = "Authorization") String token, @PathVariable String cpf) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Funcionario");

        return new ResponseEntity<>(service.get(cpf, token), headers, HttpStatus.ACCEPTED);
    }
    
    @PutMapping()
    public ResponseEntity<Void> put(@Valid @RequestBody FuncionarioDTOModel funcionario, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Atualizado");

        return new ResponseEntity<>(service.put(funcionario, token), headers, HttpStatus.OK);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Void> updateFuncionario(@PathVariable String cpf, @Valid @RequestBody FuncionarioDTOModel funcionario, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Atualizado");

        return new ResponseEntity<>(service.updateFuncionario(cpf, funcionario, token), headers, HttpStatus.OK);
    }


}
