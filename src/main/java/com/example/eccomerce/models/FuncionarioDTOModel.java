package com.example.eccomerce.models;

import java.lang.reflect.Field;

public class FuncionarioDTOModel {

    private String email;
    private String username;
    private String senha;
    private Integer role = 1;

    private String nome;
    private Long cpf;
    private Long telefone;
    private String nascimento;

    public FuncionarioDTOModel() {
        super();
    }

    public FuncionarioDTOModel(String email, String username, String senha, String nome, Long cpf, Long telefone, String nascimento) {
        super();
        this.email = email;
        this.username = username;
        this.senha = senha;

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

    public String getNascimento() {
        return nascimento;
    }

    public Boolean isNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            if(f.get(this) == null) return true;
        }
        return false;
    }
}
