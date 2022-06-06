package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.EnderecoModel;
import com.example.eccomerce.models.ViaCepModel;
import com.example.eccomerce.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @GetMapping
    public ResponseEntity<List<EnderecoModel>> getAll(@RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Lista dos seus endereços");

        return new ResponseEntity<>(service.getAll(token), headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoModel> get(@PathVariable Integer id, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Seu endereço");

        return new ResponseEntity<>(service.get(id, token), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Void> add(@Valid @RequestBody ViaCepModel endereco, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException, IllegalAccessException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Adicionado");

        return new ResponseEntity<>(service.add(endereco, token), headers, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@Valid @RequestBody ViaCepModel endereco, @PathVariable Integer id, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException, IllegalAccessException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Atualizado");

        return new ResponseEntity<>(service.put(endereco, id, token), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Deletado");

        return new ResponseEntity<>(service.delete(id, token), headers, HttpStatus.OK);
    }

}
