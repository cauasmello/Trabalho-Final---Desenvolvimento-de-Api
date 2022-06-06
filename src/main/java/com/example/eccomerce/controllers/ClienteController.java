package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.ClienteDTOModel;
import com.example.eccomerce.models.ClienteModel;
import com.example.eccomerce.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
    ClienteService service;

    @GetMapping
    public ResponseEntity<ClienteModel> get(@RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Seu endereço");

        return new ResponseEntity<>(service.get(token), headers, HttpStatus.ACCEPTED);
    }
    
    @PutMapping()
    public ResponseEntity<Void> put(@RequestBody ClienteDTOModel cliente, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Atualizado");

        return new ResponseEntity<>(service.put(cliente, token), headers, HttpStatus.OK);
    }

}
