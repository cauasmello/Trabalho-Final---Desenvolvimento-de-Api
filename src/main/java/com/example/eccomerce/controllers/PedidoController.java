package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.PedidoModel;
import com.example.eccomerce.models.PedidoProdutoDTOModel;
import com.example.eccomerce.models.ProdutoDTOModel;
import com.example.eccomerce.services.PedidoProdutoService;
import com.example.eccomerce.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService service;

    @Autowired
    PedidoProdutoService pedidoProdutoService;

    @GetMapping
    public ResponseEntity<List<PedidoModel>> getAll(@RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Lista de pedidos");

        return new ResponseEntity<>(service.getAll(token), headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<PedidoModel> get(@PathVariable String numero, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Pedido");

        return new ResponseEntity<>(service.get(numero, token), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<PedidoModel> add(@RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Adicionado");

        return new ResponseEntity<>(service.add(token), headers, HttpStatus.CREATED);

    }

    @GetMapping("/{numero}/produtos")
    public ResponseEntity<List<ProdutoDTOModel>> getProdutos(@PathVariable String numero, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Produtos");

        return new ResponseEntity<>(pedidoProdutoService.get(numero, token), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping("/{numero}/addProduto")
    public ResponseEntity<Void> addProduto(@RequestBody PedidoProdutoDTOModel produto, @PathVariable String numero, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Adicionado");

        return new ResponseEntity<>(pedidoProdutoService.add(numero, token, produto), headers, HttpStatus.CREATED);

    }

    @DeleteMapping("/{numero}/{produto}")
    public ResponseEntity<Void> deleteProdutos(@PathVariable String numero, @PathVariable Integer produto, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Deletado");

        return new ResponseEntity<>(pedidoProdutoService.delete(numero, produto, token), headers, HttpStatus.OK);
    }

    @PutMapping("/{numero}")
    public ResponseEntity<Void> liberar(@PathVariable String numero, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "finalizado");

        return new ResponseEntity<>(service.liberar(numero, token), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> delete(@PathVariable String numero, @RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Deletado");

        return new ResponseEntity<>(service.delete(numero, token), headers, HttpStatus.OK);
    }

}
