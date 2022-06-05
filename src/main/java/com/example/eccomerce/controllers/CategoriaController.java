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
	        headers.add("Status", "Lista das suas categorias");

	        return new ResponseEntity<>(service.getAll(), headers, HttpStatus.ACCEPTED);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<CategoriaModel> get(@PathVariable Integer id) throws ErrorException {
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Status", "Sua categoria");

	        return new ResponseEntity<>(service.get(id), headers, HttpStatus.ACCEPTED);
	    }

	    @PostMapping
	    public ResponseEntity<String> add(@RequestBody CategoriaModel categoria) throws ErrorException {
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Status", "Adicionado");

	        return new ResponseEntity<>(service.add(categoria), headers, HttpStatus.CREATED);

	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<String> put(@RequestBody CategoriaModel categoria, @PathVariable Integer id) throws ErrorException {
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Status", "Atualizado");

	        return new ResponseEntity<>(service.put(categoria, id), headers, HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> delete(@PathVariable Integer id) throws ErrorException {
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Status", "Deletado");

	        return new ResponseEntity<>(service.delete(id), headers, HttpStatus.OK);
	    }

}
