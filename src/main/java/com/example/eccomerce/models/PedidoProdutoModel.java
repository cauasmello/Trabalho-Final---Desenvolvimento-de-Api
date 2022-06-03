package com.example.eccomerce.models;

import javax.persistence.*;

@Entity
public class PedidoProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double valor;

    private Integer quantidade;

    @ManyToOne()
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private ProdutoModel produto;

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
}
