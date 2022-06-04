package com.example.eccomerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
public class FuncionarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "cpf", unique = true)
    private Long cpf;

    @NotNull
    @Column(name = "telefone")
    private Long telefone;

    @NotNull
    @Column(name = "nascimento")
    private LocalDate nascimento;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    public FuncionarioModel() {
        super();
    }

    public FuncionarioModel(Integer id, String nome, Long cpf, Long telefone, LocalDate nascimento, UserModel user) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.user = user;
    }

    public FuncionarioModel(FuncrionarioDTOModel funcionario, UserModel user) {
        super();
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.telefone = funcionario.getTelefone();
        this.nascimento = funcionario.getNascimento();
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
