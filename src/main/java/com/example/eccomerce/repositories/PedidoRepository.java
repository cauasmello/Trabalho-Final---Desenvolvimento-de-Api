package com.example.eccomerce.repositories;

import com.example.eccomerce.models.ClienteModel;
import com.example.eccomerce.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {

    @Query("SELECT C FROM PedidoModel C where C.cliente=:cliente and C.numero=:numero")
    Optional<PedidoModel> findByClienteAndNumero(@Param("cliente") ClienteModel cliente, @Param("numero") String numero);

    @Query("SELECT C FROM PedidoModel C where C.numero=:numero")
    Optional<PedidoModel> findByNumero(@Param("numero") String numero);

    @Query("SELECT count(C) FROM PedidoModel C where C.cliente=:cliente and C.status like 'Aberto'")
    Integer existPedidoAberto(@Param("cliente") ClienteModel cliente);
}
