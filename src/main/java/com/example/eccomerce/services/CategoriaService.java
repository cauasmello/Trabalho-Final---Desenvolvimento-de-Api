package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.CategoriaModel;
import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.repositories.CategoriaRepository;
import com.example.eccomerce.resources.ToolsResource;
import com.example.eccomerce.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repositorio;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ToolsResource tools;

    public List<CategoriaModel> getAll() {
        List<CategoriaModel> list = repositorio.findAll();
        System.out.println(list);
        return list;
    }

    public CategoriaModel get(String name) throws ErrorException {
        Optional<CategoriaModel> optional = repositorio.findByNome(name);
        if (optional.isEmpty()) {
            throw new ErrorException("Categoria não existe!");
        }
        return optional.get();
    }

    public Void add(CategoriaModel categoria, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        Optional<CategoriaModel> optional = repositorio.findByNome(categoria.getNome());
        if (optional.isPresent()) {
            throw new ErrorException("Categoria já existe!");
        }

        if(categoria.getNome() == null || categoria.getNome().equals("")){
            throw new ErrorException("Nome Invalido");
        }

        if(categoria.getDescricao() == null || categoria.getDescricao().equals("")){
            throw new ErrorException("Descrição Invalida");
        }

        repositorio.save(categoria);
        return null;
    }

    public Void put(CategoriaModel categoriaNew, Integer id, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        Optional<CategoriaModel> optional = repositorio.findById(id);
        if (optional.isEmpty()) {
            throw new ErrorException("Categoria não existe!");
        }
        CategoriaModel categoria = optional.get();

        if (categoriaNew.getNome() != null && !categoriaNew.getNome().equals("")) {
            categoria.setNome(categoriaNew.getNome());
        }

        if (categoriaNew.getDescricao() != null && !categoriaNew.getDescricao().equals("")) {
            categoria.setDescricao(categoriaNew.getDescricao());
        }

        repositorio.save(categoria);

        return null;

    }

    public Void delete(Integer id, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        Optional<CategoriaModel> optional = repositorio.findById(id);
        if (optional.isEmpty()) {
            throw new ErrorException("Categoria não existe!");
        }
        repositorio.deleteById(id);
        return null;
    }
}
