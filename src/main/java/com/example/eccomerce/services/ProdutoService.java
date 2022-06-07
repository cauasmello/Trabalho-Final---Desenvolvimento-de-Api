package com.example.eccomerce.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.ClienteModel;
import com.example.eccomerce.models.ProdutoDTOModel;
import com.example.eccomerce.models.ProdutoModel;
import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.repositories.ImagemProdutoRepository;
import com.example.eccomerce.repositories.ProdutoRepository;
import com.example.eccomerce.resources.ToolsResource;
import com.example.eccomerce.security.JWTUtil;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	ProdutoRepository repositorio;
	
	@Autowired
	ImagemProdutoService service;

	@Autowired
	ImagemProdutoRepository imageRepository;
	
	  @Autowired
	    JWTUtil jwtUtil;

	    @Autowired
	    ToolsResource tools;


	    public List<ProdutoModel> getAll(String token) throws ErrorException {
	    	UserModel myUser = jwtUtil.getLoggedUser(token);
	        tools.existUser(myUser);
	        tools.onlyFuncionarios(myUser);
	        List<ProdutoModel> list = repositorio.findAll();
	        return list;
	    }
	  
	public ProdutoModel get(String nome, String token) throws ErrorException {
		 UserModel myUser = jwtUtil.getLoggedUser(token);
	        tools.existUser(myUser);
	        tools.onlyFuncionarios(myUser);
		Optional<ProdutoModel> optional = repositorio.findByNome(nome);
		if (optional.isEmpty()) {
			throw new ErrorException("Produto não encontrado");
		}

		return optional.get();
	}

	
	public ProdutoDTOModel getProdutoDTO(String nome, String token) throws ErrorException {
		UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);
		ProdutoModel produto = this.get(nome, token);
		ProdutoDTOModel produtoDTO = new ProdutoDTOModel();
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setUrl(service.generateURL(produto.getId()));
		return produtoDTO;
	}
	
	public String add(ProdutoModel produtoNew, MultipartFile file, String token) throws IOException, ErrorException {
		 UserModel myUser = jwtUtil.getLoggedUser(token);
	      tools.existUser(myUser);
	      tools.onlyFuncionarios(myUser);
		ProdutoModel produtoOld = repositorio.save(produtoNew);
		service.add(produtoNew, file);
		return "Produto inserido com sucesso";
	}
	

	public ProdutoModel update(String nome, ProdutoModel produtoNew, String token) throws ErrorException {
		 UserModel myUser = jwtUtil.getLoggedUser(token);
	        tools.existUser(myUser);
	        ClienteModel clienteModel = myUser.getCliente();
	        tools.existCliente(clienteModel);

	        Optional <ProdutoModel> optional = repositorio.findByNome(nome);

	        if (optional.isEmpty()) {
	            throw new ErrorException("Produto não existe!");
	        }
	        ProdutoModel produto = optional.get();

	        if (produtoNew.getNome() != null && !produtoNew.getNome().equals("")) {
	            produto.setNome(produtoNew.getNome());
	        }

	        if (produtoNew.getDescricao() != null && !produtoNew.getDescricao().equals("")) {
	            produto.setDescricao(produtoNew.getDescricao());
	        }
	        
	        if (produtoNew.getValor() != null && !produtoNew.getValor().equals("")) {
	            produto.setValor(produtoNew.getValor());
	        }
	        
	        if (produtoNew.getQuantidade_estoque() != null && !produtoNew.getQuantidade_estoque().equals("")) {
	            produto.setQuantidade_estoque(produtoNew.getQuantidade_estoque());
	        }



	        repositorio.save(produto);

	        return null;
	}



	public String delete(String nome, String token) throws ErrorException {
		UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        ClienteModel clienteModel = myUser.getCliente();
        tools.existCliente(clienteModel);
		repositorio.deleteByNome(nome);;
		return "Deletado com sucesso";
	}
	
}
