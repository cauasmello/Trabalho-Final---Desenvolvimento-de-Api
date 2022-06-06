package com.example.eccomerce.repositories;

import com.example.eccomerce.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

    @Query("SELECT count(C) FROM ClienteModel C where C.cpf=:cpf")
    Integer existCPF(@Param("cpf") Long cpf);
}
