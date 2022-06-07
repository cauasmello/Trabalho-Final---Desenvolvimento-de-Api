package com.example.eccomerce.controllers;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.ImagemProdutoModel;
import com.example.eccomerce.models.ProdutoDTOModel;
import com.example.eccomerce.models.ProdutoModel;
import com.example.eccomerce.security.JWTUtil;
import com.example.eccomerce.services.ImagemProdutoService;
import com.example.eccomerce.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    ProdutoService service;

    @Autowired
    ImagemProdutoService imageService;

    @Autowired
    JWTUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<ProdutoDTOModel>> getAll() throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Lista dos seus Produtos");

        return new ResponseEntity<>(service.getAll(), headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<ProdutoDTOModel> getbyNomeDTO(@PathVariable String nome) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Produto por nome");
        return new ResponseEntity<ProdutoDTOModel>(service.get(nome), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Void> postProduto(@RequestPart ProdutoModel produto, @RequestParam MultipartFile file, @RequestHeader(name = "Authorization") String token) throws ErrorException, IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Produto Inserido");
        return new ResponseEntity<>(service.add(produto, file, token), headers, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> putProduto(@RequestBody ProdutoModel produto, @PathVariable Integer id, @RequestHeader(name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Produto Atualizado");
        return new ResponseEntity<>(service.update(id, produto, token), headers, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Integer id, @RequestHeader(name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Success", "Produto Deletado");
        return new ResponseEntity<>(service.delete(id, token), headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}/imagem")
    public ResponseEntity<byte[]> getImagem(@PathVariable Integer id) {
        ImagemProdutoModel imagem = imageService.getImagem(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", imagem.getMimeType());
        headers.add("content-lenght", String.valueOf(imagem.getData().length));
        return new ResponseEntity<>(imagem.getData(), headers, HttpStatus.OK);
    }

}
