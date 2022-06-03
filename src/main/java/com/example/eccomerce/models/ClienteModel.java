package com.example.eccomerce.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private Long cpf;

    private Long telefone;

    private Date nascimento;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    public ClienteModel() {
        super();
    }

    public ClienteModel(Integer id, String nome, Long cpf, Long telefone, Date nascimento, UserModel user) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.user = user;
    }
}