package com.example.eccomerce.repositories;

import com.example.eccomerce.models.CategoriaModel;
import com.example.eccomerce.models.FuncionarioModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Integer> {

    @Query("SELECT count(C) FROM FuncionarioModel C where C.cpf=:cpf")
    Integer existCPF(@Param("cpf") Long cpf);
    
    @Query("SELECT C FROM FuncionarioModel C where C.cpf=:cpf")
    Optional<FuncionarioModel> findByCpf(@Param("cpf")Long cpf);
}
