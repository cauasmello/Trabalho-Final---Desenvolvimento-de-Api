package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.CategoriaModel;
import com.example.eccomerce.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @GetMapping
    public ResponseEntity<List<CategoriaModel>> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Lista de categorias");

        return new ResponseEntity<>(service.getAll(), headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoriaModel> get(@PathVariable String name) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Categoria");

        return new ResponseEntity<>(service.get(name), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody CategoriaModel categoria, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Adicionado");

        return new ResponseEntity<>(service.add(categoria, token), headers, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@RequestBody CategoriaModel categoria, @PathVariable Integer id, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Atualizado");

        return new ResponseEntity<>(service.put(categoria, id, token), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Deletado");

        return new ResponseEntity<>(service.delete(id, token), headers, HttpStatus.OK);
    }

}
