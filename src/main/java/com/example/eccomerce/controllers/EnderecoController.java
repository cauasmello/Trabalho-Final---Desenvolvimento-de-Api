package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.GeneralException;
import com.example.eccomerce.models.EnderecoModel;
import com.example.eccomerce.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @GetMapping
    public ResponseEntity<List<EnderecoModel>> getAll() throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Lista de Endereços", "Segue a lista de Endereços");

        return new ResponseEntity<List<EnderecoModel>>(service.getAll(), headers, HttpStatus.valueOf(202));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoModel> get(@PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Endereco", "Retornado");

        return new ResponseEntity<EnderecoModel>(service.get(id), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<EnderecoModel> add(@RequestBody EnderecoModel endereco) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Endereco", "Adicionado");

        return new ResponseEntity<EnderecoModel>(service.add(endereco), headers, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoModel> put(@RequestBody EnderecoModel endereco, @PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Endereco", "Atualizado");

        return new ResponseEntity<EnderecoModel>(service.put(endereco, id), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Endereco", "Deletado");

        return new ResponseEntity<String>(service.delete(id), headers, HttpStatus.OK);
    }

}
