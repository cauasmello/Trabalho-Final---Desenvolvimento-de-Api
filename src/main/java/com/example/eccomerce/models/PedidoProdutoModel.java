package com.example.eccomerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido_produtos")
public class PedidoProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "valor")
    private Double valor;

    @NotNull
    @Column(name = "quantidade")
    private Integer quantidade;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private ProdutoModel produto;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private PedidoModel pedido;

    public PedidoProdutoModel() {
        super();
    }

    public PedidoProdutoModel(Integer id, Double valor, Integer quantidade, ProdutoModel produto, PedidoModel pedido) {
        super();
        this.id = id;
        this.valor = valor;
        this.quantidade = quantidade;
        this.produto = produto;
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }
}
