package com.example.eccomerce.models;

import java.time.LocalDate;
import java.util.List;

public class PedidoDTOModel {

    private Integer id;
    private String numero;
    private Double valor;
    private LocalDate criado;
    private LocalDate entrege;
    private String status;
    private List<ProdutoDTOModel> produto;

    public PedidoDTOModel() {
        super();
    }

    public PedidoDTOModel(PedidoModel pedidoModel, List<ProdutoDTOModel> produtoDTOModel) {
        super();
        this.id = pedidoModel.getId();
        this.numero = pedidoModel.getNumero();
        this.valor = pedidoModel.getValor();
        this.criado = pedidoModel.getCriado();
        this.entrege = pedidoModel.getEntrege();
        this.status = pedidoModel.getStatus();
        this.produto = produtoDTOModel;
    }

    public PedidoDTOModel(PedidoModel pedidoModel) {
        super();
        this.id = pedidoModel.getId();
        this.numero = pedidoModel.getNumero();
        this.valor = pedidoModel.getValor();
        this.criado = pedidoModel.getCriado();
        this.entrege = pedidoModel.getEntrege();
        this.status = pedidoModel.getStatus();
    }
}
