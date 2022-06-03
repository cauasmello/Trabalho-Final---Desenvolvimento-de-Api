package com.example.eccomerce.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;

    private Double valor;

    private Integer quantidade_estoque;

    private Date criado;

    private String img;

    @ManyToOne()
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private CategoriaModel categorio;

    @ManyToOne()
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    private FuncionarioModel funcionario;

    public ProdutoModel() {
        super();
    }

    public ProdutoModel(String nome, String descricao, Double valor, Integer quantidade_estoque, String img, CategoriaModel categorio, FuncionarioModel funcionario) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade_estoque = quantidade_estoque;
        this.img = img;
        this.categorio = categorio;
        this.funcionario = funcionario;
    }
}
