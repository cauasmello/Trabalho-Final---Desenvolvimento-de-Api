package com.example.eccomerce.controller;

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

import com.example.eccomerce.exception.EndereçoExisteException;
import com.example.eccomerce.exception.EndereçoNotException;
import com.example.eccomerce.model.Endereco;
import com.example.eccomerce.service.ServiceEndereco;


@RestController
@RequestMapping("/endereço")
public class ControllerEndereco {
	
	@Autowired
	ServiceEndereco service;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> getAll(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Lista de Endereços", "Segue a lista de Endereços");
		return new ResponseEntity<List<Endereco>>(service.listaTudo(),headers,HttpStatus.valueOf(202));
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Endereco> getOne(@PathVariable Integer id) throws EndereçoNotException{
    HttpHeaders headers = new HttpHeaders();
    headers.add("Endereço", "Retornado");
    return new ResponseEntity<Endereco>(service.listaEnderecos(id), headers, HttpStatus.ACCEPTED);
}

	@PostMapping
public ResponseEntity<Endereco> addEndereco(@RequestBody Endereco endereco) throws EndereçoExisteException {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Endereço", "Adicionado");
    return new ResponseEntity<Endereco>(service.inserir(endereco), headers, HttpStatus.CREATED);

}

		@PutMapping("/{id}")
public ResponseEntity<?> putEndereco(@RequestBody Endereco endereco, @PathVariable Integer id) throws EndereçoExisteException, EndereçoNotException{
    HttpHeaders headers = new HttpHeaders();
    headers.add("Endereco", "Atualizado");
    return new ResponseEntity<>(service.atualizarPorId(endereco, id), headers, HttpStatus.OK);
}

		@DeleteMapping("/{id}")
public ResponseEntity<String> deleteEndereco(@PathVariable Integer id) throws EndereçoNotException{
    HttpHeaders headers = new HttpHeaders();
    headers.add("Endereco", "Deletado");
    return new ResponseEntity<String>(service.removerPorId(id), headers, HttpStatus.OK);
}
	
	

}
