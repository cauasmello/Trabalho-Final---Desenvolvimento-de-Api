package com.example.eccomerce.models;

import java.lang.reflect.Field;

public class ClienteDTOModel {

    private String email;
    private String username;
    private String senha;
    private Integer role = 0;

    private String nome;
    private Long cpf;
    private Long telefone;
    private String nascimento;

    private Long cep;
    private Integer numero;
    private String complemento;

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

    public ClienteDTOModel() {
        super();
    }

    public ClienteDTOModel(String email, String username, String senha, String nome, Long cpf, Long telefone, String nascimento, Long cep, Integer numero, String complemento) {
        super();
        this.email = email;
        this.username = username;
        this.senha = senha;

        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.nascimento = nascimento;

        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void setViaCep(ViaCepModel viaCepModel){
        this.rua = viaCepModel.getLogradouro();
        this.bairro = viaCepModel.getBairro();
        this.cidade = viaCepModel.getLocalidade();
        this.estado = viaCepModel.getUf();
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

    public Long getCep() {
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

    public Integer getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getEstado() {
        return estado;
    }

    public Boolean isNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            if(f.get(this) == null) return true;
        }
        return false;
    }
}
