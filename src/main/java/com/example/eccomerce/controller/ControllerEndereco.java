package com.example.eccomerce.controller;

import com.example.eccomerce.exception.GeneralException;
import com.example.eccomerce.model.EnderecoModel;
import com.example.eccomerce.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/endereco")
public class ControllerEndereco {

    @Autowired
    EnderecoService service;

    @GetMapping
    public ResponseEntity<List<EnderecoModel>> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Lista de Endereços", "Segue a lista de Endereços");
        return new ResponseEntity<List<EnderecoModel>>(service.listaTudo(), headers, HttpStatus.valueOf(202));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoModel> getOne(@PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Endereco", "Retornado");
        return new ResponseEntity<EnderecoModel>(service.listaEnderecos(id), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<EnderecoModel> addEndereco(@RequestBody EnderecoModel endereco) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Endereco", "Adicionado");
        return new ResponseEntity<EnderecoModel>(service.inserir(endereco), headers, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putEndereco(@RequestBody EnderecoModel endereco, @PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Endereco", "Atualizado");
        return new ResponseEntity<>(service.atualizarPorId(endereco, id), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEndereco(@PathVariable Integer id) throws GeneralException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Endereco", "Deletado");
        return new ResponseEntity<String>(service.removerPorId(id), headers, HttpStatus.OK);
    }

}
