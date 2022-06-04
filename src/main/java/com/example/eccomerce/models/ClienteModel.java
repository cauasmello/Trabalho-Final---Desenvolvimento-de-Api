package com.example.eccomerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clientes")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @CPF
    @NotNull
    @Column(name = "cpf", unique = true)
    private Long cpf;

    @NotNull
    @Column(name = "telefone")
    private Long telefone;

    @NotNull
    @Column(name = "nascimento")
    private LocalDate nascimento;

    @OneToMany(mappedBy = "cliente")
    private List<PedidoModel> pedidos;

    @OneToMany(mappedBy = "cliente")
    private List<EnderecoModel> endereco;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    public ClienteModel() {
        super();
    }

    public ClienteModel(Integer id, String nome, Long cpf, Long telefone, LocalDate nascimento, List<PedidoModel> pedidos, List<EnderecoModel> endereco, UserModel user) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.pedidos = pedidos;
        this.endereco = endereco;
        this.user = user;
    }

    public ClienteModel(ClienteDTOModel clienteDTO, UserModel user) {
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
        this.nascimento = clienteDTO.getNascimento();
        this.telefone = clienteDTO.getTelefone();
        this.user = user;
    }

    public Integer getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return this.cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTelefone() {
        return this.telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public LocalDate getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public List<PedidoModel> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoModel> pedidos) {
        this.pedidos = pedidos;
    }

    public List<EnderecoModel> getEnderecos() {
        return endereco;
    }

    public void setEnderecos(List<EnderecoModel> enderecos) {
        this.endereco = enderecos;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
