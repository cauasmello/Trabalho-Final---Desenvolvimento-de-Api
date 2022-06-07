package com.example.eccomerce.repositories;

import com.example.eccomerce.models.PedidoModel;
import com.example.eccomerce.models.PedidoProdutoModel;
import com.example.eccomerce.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoModel, Integer> {

    @Query("SELECT C FROM PedidoProdutoModel C where C.pedido=:pedido and C.produto=:produto")
    Optional<PedidoProdutoModel> findByPedidoAndProduto(@Param("pedido") PedidoModel pedido, @Param("produto") ProdutoModel produto);
}
