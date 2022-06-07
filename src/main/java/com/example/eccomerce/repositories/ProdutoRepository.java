package com.example.eccomerce.repositories;

import com.example.eccomerce.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {

    @Query("SELECT C FROM ProdutoModel C where C.nome=:nome")
    Optional<ProdutoModel> findByNome(@Param("nome") String nome);

}
