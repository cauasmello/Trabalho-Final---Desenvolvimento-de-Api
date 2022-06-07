package com.example.eccomerce.repositories;

import com.example.eccomerce.models.ProdutoModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {
	
	  @Query("SELECT C FROM ProdutoModel C where C.nome=:nome")
	    Optional<ProdutoModel> findByNome(@Param("nome") String nome);
	  
	  void deleteByNome(String nome);
}
