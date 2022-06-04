package com.example.eccomerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eccomerce.exceptions.GeneralException;
import com.example.eccomerce.models.ClienteModel;
import com.example.eccomerce.models.ClienteModel;
import com.example.eccomerce.services.ClienteService;
import com.example.eccomerce.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
    ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteModel>> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Lista dos seus endereços");

        return new ResponseEntity<>(service.getAll(), headers, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> get(@PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Seu endereço");

        return new ResponseEntity<>(service.get(id), headers, HttpStatus.ACCEPTED);
    }
    
    @PostMapping
    public ResponseEntity<String> add(@RequestBody ClienteModel cliente) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Adicionado");

        return new ResponseEntity<>(service.add(cliente), headers, HttpStatus.CREATED);

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> put(@RequestBody ClienteModel cliente, @PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Atualizado");

        return new ResponseEntity<>(service.put(cliente, id), headers, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Deletado");

        return new ResponseEntity<>(service.delete(id), headers, HttpStatus.OK);
    }

}
