package com.example.eccomerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuarios")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "senha")
    private String senha;

    @Column(name = "role")
    private Integer role;

    @JsonIgnore
    @OneToOne(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ClienteModel cliente;

    @JsonIgnore
    @OneToOne(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private FuncionarioModel funcionario;

    public UserModel() {
        super();
    }

    public UserModel(Integer id, String email, String username, String senha, Integer role, ClienteModel cliente) {
        super();
        this.id = id;
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.role = role;
        this.cliente = cliente;
    }

    public UserModel(Integer id, String email, String username, String senha, Integer role, FuncionarioModel funcionario) {
        super();
        this.id = id;
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.role = role;
        this.funcionario = funcionario;
    }

    public UserModel(FuncionarioDTOModel funcionario) {
        super();
        this.email = funcionario.getEmail();
        this.username = funcionario.getUsername();
        this.senha = funcionario.getSenha();
        this.role = funcionario.getRole();
    }

    public UserModel(ClienteDTOModel cliente) {
        super();
        this.email = cliente.getEmail();
        this.username = cliente.getUsername();
        this.senha = cliente.getSenha();
        this.role = cliente.getRole();
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getRole() {
        return role;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public FuncionarioModel getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioModel funcionario) {
        this.funcionario = funcionario;
    }
}
