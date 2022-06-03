package com.example.eccomerce.models;

import javax.persistence.*;

@Entity
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;

    public CategoriaModel() {
        super();
    }

    public CategoriaModel(Integer id, String nome, String descricao) {
        super();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
}
