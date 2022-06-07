package com.example.eccomerce.models;

public class PedidoProdutoDTOModel {
    private Integer quantidade;

    private Integer produto_id;

    public PedidoProdutoDTOModel() {
        super();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Integer produto_id) {
        this.produto_id = produto_id;
    }
}
