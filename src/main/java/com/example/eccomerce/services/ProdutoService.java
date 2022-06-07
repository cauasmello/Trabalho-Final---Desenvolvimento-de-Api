package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.*;
import com.example.eccomerce.repositories.CategoriaRepository;
import com.example.eccomerce.repositories.ImagemProdutoRepository;
import com.example.eccomerce.repositories.ProdutoRepository;
import com.example.eccomerce.resources.ToolsResource;
import com.example.eccomerce.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    ProdutoRepository repositorio;

    @Autowired
    ImagemProdutoRepository imagemProdutoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ImagemProdutoService imagemProdutoService;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ToolsResource tools;

    public List<ProdutoDTOModel> getAll() throws ErrorException {
        List<ProdutoModel> produtoModels = repositorio.findAll();
        List<ProdutoDTOModel> produtoDTOModels = new ArrayList<>();
        for(ProdutoModel produto : produtoModels){
            produtoDTOModels.add(new ProdutoDTOModel(produto, imagemProdutoService.generateURL(produto.getId())));
        }

        return produtoDTOModels;
    }

    public ProdutoDTOModel get(String nome) throws ErrorException {
        Optional<ProdutoModel> optional = repositorio.findByNome(nome);
        if (optional.isEmpty()) {
            throw new ErrorException("Produto não existe!");
        }
        ProdutoModel produto = optional.get();

        return new ProdutoDTOModel(produto, imagemProdutoService.generateURL(produto.getId()));
    }

    public Void add(ProdutoDTOModel produtoNew, MultipartFile file, String token) throws IOException, ErrorException, IllegalAccessException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        if (produtoNew.getNome() == null || produtoNew.getNome().equals("")) {
            throw new ErrorException("Informe o nome!");
        }

        if (produtoNew.getDescricao() == null || produtoNew.getDescricao().equals("")) {
            throw new ErrorException("Informe a descrição!");
        }

        if (produtoNew.getValor() == null) {
            throw new ErrorException("Informe o valor!");
        }

        if (produtoNew.getQuantidade_estoque() == null) {
            throw new ErrorException("Informe a quantidade no estoque!");
        }

        if (produtoNew.getCategoria_id() == null) {
            throw new ErrorException("Informe o id da categoria!");
        }

        if(file.isEmpty()){
            throw new ErrorException("Imagem invalidas!");
        }

        Optional<CategoriaModel> optional = categoriaRepository.findById(produtoNew.getCategoria_id());

        if(optional.isEmpty()){
            throw new ErrorException("Categoria não existe!");
        }

        Optional<ProdutoModel> optional2 = repositorio.findByNome(produtoNew.getNome());

        if(optional2.isPresent()){
            throw new ErrorException("Produto já existe!");
        }

        ProdutoModel produto = new ProdutoModel(produtoNew, optional.get(), myUser.getFuncionario());

        repositorio.save(produto);
        imagemProdutoService.add(produto, file);
        return null;
    }

    public Void update(ProdutoDTOModel produtoNew, Integer id, MultipartFile file, String token) throws ErrorException, IOException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        Optional<ProdutoModel> optional = repositorio.findById(id);

        if (optional.isEmpty()) {
            throw new ErrorException("Produto não existe!");
        }
        ProdutoModel produto = optional.get();

        if (produtoNew.getNome() != null && !produtoNew.getNome().equals("")) {
            Optional<ProdutoModel> optional2 = repositorio.findByNome(produtoNew.getNome());
            if(optional2.isPresent()){
                throw new ErrorException("Produto já existe!");
            }
            produto.setNome(produtoNew.getNome());
        }

        if (produtoNew.getDescricao() != null && !produtoNew.getDescricao().equals("")) {
            produto.setDescricao(produtoNew.getDescricao());
        }

        if (produtoNew.getValor() != null) {
            produto.setValor(produtoNew.getValor());
        }

        if (produtoNew.getQuantidade_estoque() != null) {
            produto.setQuantidade_estoque(produtoNew.getQuantidade_estoque());
        }

        repositorio.save(produto);

        if(!file.isEmpty()){
            ImagemProdutoModel image = produto.getImagem();
            image.setMimeType(file.getContentType());
            image.setName(file.getName());
            image.setData(file.getBytes());
            imagemProdutoRepository.save(image);
        }

        return null;
    }

    public Void delete(Integer id, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        ClienteModel clienteModel = myUser.getCliente();
        tools.existCliente(clienteModel);

        Optional<ProdutoModel> optional = repositorio.findById(id);
        if (optional.isEmpty()) {
            throw new ErrorException("Produto não existe!");
        }
        repositorio.deleteById(optional.get().getId());

        return null;
    }

}
