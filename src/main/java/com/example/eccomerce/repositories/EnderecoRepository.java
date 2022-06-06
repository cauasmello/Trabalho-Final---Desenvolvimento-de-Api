package com.example.eccomerce.repositories;

import com.example.eccomerce.models.ClienteModel;
import com.example.eccomerce.models.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository <EnderecoModel, Integer> {

    @Query("SELECT count(C) FROM EnderecoModel C where C.cep=:cep")
    Integer existCEP(@Param("cep") Long cep);

    @Query("SELECT C FROM EnderecoModel C where C.cliente=:cliente and C.id=:id")
    Optional<EnderecoModel> findByClienteAndID(@Param("cliente") ClienteModel cliente, @Param("id") Integer id);
}
