package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.ClienteModel;
import com.example.eccomerce.models.ProdutoDTOModel;
import com.example.eccomerce.models.ProdutoModel;
import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.repositories.ImagemProdutoRepository;
import com.example.eccomerce.repositories.ProdutoRepository;
import com.example.eccomerce.resources.ToolsResource;
import com.example.eccomerce.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    ProdutoRepository repositorio;

    @Autowired
    ImagemProdutoService imagemProdutoService;

    @Autowired
    ImagemProdutoRepository imageRepository;

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

    public Void add(ProdutoModel produtoNew, MultipartFile file, String token) throws IOException, ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Date date = new Date();
        produtoNew.setCriado(LocalDate.parse(date.toString(), formatter));

        repositorio.save(produtoNew);
        imagemProdutoService.add(produtoNew, file);
        return null;
    }

    public Void update(Integer id, ProdutoModel produtoNew, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        ClienteModel clienteModel = myUser.getCliente();
        tools.existCliente(clienteModel);

        Optional<ProdutoModel> optional = repositorio.findById(id);

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
