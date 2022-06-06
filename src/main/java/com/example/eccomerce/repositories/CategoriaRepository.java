package com.example.eccomerce.repositories;

import com.example.eccomerce.models.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Integer> {

    @Query("SELECT C FROM CategoriaModel C where C.nome=:nome")
    Optional<CategoriaModel> findByNome(@Param("nome") String nome);
}
