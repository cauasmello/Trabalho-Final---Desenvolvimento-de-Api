package com.example.eccomerce.models;

import java.time.LocalDate;

public class ClienteDTOModel {

    private String email;
    private String username;
    private String senha;
    private Integer role;

    private String nome;
    private Long cpf;
    private Long telefone;
    private LocalDate nascimento;

    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String numero;
    private String complemento;
    private String estado;

    public ClienteDTOModel() {
        super();
    }

    public ClienteDTOModel(String email, String username, String senha, String nome, Long cpf, Long telefone, LocalDate nascimento, ViaCepModel viaCep) {
        super();
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.role = 0;

        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.nascimento = nascimento;

        this.cep = viaCep.getCep();
        this.rua = viaCep.getLogradouro();
        this.bairro = viaCep.getBairro();
        this.cidade = viaCep.getLocalidade();
        this.numero = viaCep.getNumero();
        this.complemento = viaCep.getComplemento();
        this.estado = viaCep.getUf();
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

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getEstado() {
        return estado;
    }
}
