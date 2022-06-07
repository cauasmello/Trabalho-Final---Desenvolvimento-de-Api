package com.example.eccomerce.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.ImagemProdutoModel;
import com.example.eccomerce.models.ProdutoDTOModel;
import com.example.eccomerce.models.ProdutoModel;
import com.example.eccomerce.security.JWTUtil;
import com.example.eccomerce.services.ImagemProdutoService;
import com.example.eccomerce.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	ProdutoService serviceproduto;
	
	@Autowired
	ImagemProdutoService imageService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> getAll(@RequestHeader(required = true, name = "Authorization") String token) throws ErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successo", "Lista dos seus Produtos");

        return new ResponseEntity<>(serviceproduto.getAll(token), headers, HttpStatus.ACCEPTED);
    }
	
	@GetMapping("/{nome}")
	public ResponseEntity <ProdutoModel> getbyNome(@RequestHeader(required = true, name = "Authorization") @PathVariable String nome, String token) throws ErrorException{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Produto", "Produto por nome");
		return new ResponseEntity <ProdutoModel>(serviceproduto.get(nome, token), headers, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity <ProdutoDTOModel> getbyNomeDTO(@RequestHeader(required = true, name = "Authorization") @PathVariable String nome, String token) throws ErrorException{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Produto", "Produto por nome");
		return new ResponseEntity <ProdutoDTOModel>(serviceproduto.getProdutoDTO(nome, token), headers, HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping
	public ResponseEntity<String> postProduto(@RequestPart ProdutoModel produto, @RequestParam MultipartFile file, @RequestHeader(name="Authorization") String token) throws ErrorException, IOException {
		if(!jwtUtil.getLoggedUser(token).equals("funcionario")) {
			throw new ErrorException("Você não pode acessar essa url");         
			}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Produto", "Produto Inserido");
		return new ResponseEntity<String>(serviceproduto.add(produto, file, token), headers, HttpStatus.CREATED);
	}
	

	
	@PutMapping("/{nome}")
	public ResponseEntity<ProdutoModel> putProduto(@RequestBody ProdutoModel produto, @PathVariable String nome, @RequestHeader(name="Authorization") String token) throws ErrorException {
		if(!jwtUtil.getLoggedUser(token).equals("funcionario")) {
			throw new ErrorException("Você não pode acessar essa url");         
			}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Produto", "Produto Atualizado");
		return new ResponseEntity<>(serviceproduto.update(nome, produto, token), headers, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{nome}")
	public ResponseEntity<?> deleteProduto(@PathVariable String nome, @RequestHeader(name="Authorization") String token) throws ErrorException{
		if(!jwtUtil.getLoggedUser(token).equals("funcionario")) {
			throw new ErrorException("Você não pode acessar essa url");         
			}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Produto", "Produto Deletado");
		return new ResponseEntity<>(serviceproduto.delete(nome, token), headers, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}/imagem")
	public ResponseEntity<byte[]> getImagem(@PathVariable Integer id){
		ImagemProdutoModel imagem = imageService.getImagem(id);
		HttpHeaders headers = new HttpHeaders(); 
		headers.add("content-type", imagem.getMimeType());
		headers.add("content-lenght", String.valueOf(imagem.getData().length));
		return new ResponseEntity<>(imagem.getData(), headers, HttpStatus.OK);
	}

}
