package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.GeneralException;
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
    public ResponseEntity<List<EnderecoModel>> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Lista dos seus endereços");

        return new ResponseEntity<>(service.getAll(), headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoModel> get(@PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Seu endereço");

        return new ResponseEntity<>(service.get(id), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<String> add(@Valid @RequestBody ViaCepModel endereco) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Adicionado");

        return new ResponseEntity<>(service.add(endereco), headers, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> put(@Valid @RequestBody EnderecoModel endereco, @PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Atualizado");

        return new ResponseEntity<>(service.put(endereco, id), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "Deletado");

        return new ResponseEntity<>(service.delete(id), headers, HttpStatus.OK);
    }

}
