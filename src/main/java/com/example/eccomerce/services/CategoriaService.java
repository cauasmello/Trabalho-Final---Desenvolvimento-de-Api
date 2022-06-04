package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.GeneralException;
import com.example.eccomerce.models.CategoriaModel;
import com.example.eccomerce.repositories.CategoriaRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

	 @Autowired
	    CategoriaRepository repositorio;

	    public List<CategoriaModel> getAll() {
	        List<CategoriaModel> list = repositorio.findAll();
	        System.out.println(list);
	        return list;
	    }

	    public CategoriaModel get(Integer id) throws GeneralException {
	        Optional<CategoriaModel> optional = repositorio.findById(id);
	        if (optional.isEmpty()) {
	            throw new GeneralException("Categoria não existe!");
	        }
	        return optional.get();
	    }

	    public String add(@NotNull CategoriaModel categoria) throws GeneralException {
	        if(categoria.getId() != null){
	            Optional<CategoriaModel> optional = repositorio.findById(categoria.getId());
	            if (optional.isPresent()) {
	                throw new GeneralException("Categoria já existe!");
	            }
	        }
	        repositorio.save(categoria);
	        return "Criado com sucesso!";
	    }

	    public String put(CategoriaModel categoriaNew, Integer id) throws GeneralException {
	        Optional<CategoriaModel> optional = repositorio.findById(id);
	        if (optional.isEmpty()) {
	            throw new GeneralException("Categoria não existe!");
	        }
	        CategoriaModel categoria = optional.get();

	        if (categoriaNew.getNome() != null && !categoriaNew.getNome().equals("")) {
	            categoria.setNome(categoriaNew.getNome());
	        }

	        if (categoriaNew.getDescricao() != null && !categoriaNew.getDescricao().equals("")) {
	            categoria.setDescricao(categoriaNew.getDescricao());
	        }

	        return "Categoria Atualizado";

	    }

	    public String delete(Integer id) throws GeneralException {
	        Optional<CategoriaModel> optional = repositorio.findById(id);
	        if (optional.isEmpty()) {
	            throw new GeneralException("Categoria não existe!");
	        }
	        repositorio.deleteById(id);
	        return "Categoria com sucesso";
	    }
}
