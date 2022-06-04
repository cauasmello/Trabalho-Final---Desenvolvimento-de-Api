package com.example.eccomerce.models;

import java.time.LocalDate;

public class FuncrionarioDTOModel {

    private String email;
    private String username;
    private String senha;
    private Integer role;

    private String nome;
    private Long cpf;
    private Long telefone;
    private LocalDate nascimento;

    public FuncrionarioDTOModel() {
        super();
    }

    public FuncrionarioDTOModel(String email, String username, String senha, String nome, Long cpf, Long telefone, LocalDate nascimento) {
        super();
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.role = 1;

        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public Integer getRole() {
        return role;
    }

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public Long getTelefone() {
        return telefone;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }
}
