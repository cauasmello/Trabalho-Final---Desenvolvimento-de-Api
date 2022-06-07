package com.example.eccomerce.repositories;

import com.example.eccomerce.models.ImagemProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagemProdutoRepository  extends JpaRepository<ImagemProdutoModel, Integer>{

    Optional<ImagemProdutoModel> findByProdutoId(Integer id);
}
