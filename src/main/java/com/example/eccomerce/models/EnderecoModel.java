package com.example.eccomerce.models;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;


@Entity
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String cep;

    @NotNull
    private String rua;

    @NotNull
    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    private String numero;

    @NotNull
    private String complemento;

    @NotNull
    private String estado;

    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteModel cliente;

    public EnderecoModel() {
        super();
    }

    public EnderecoModel(Integer id, String cep, String rua, String bairro, String cidade, String numero, String complemento, String estado, ClienteModel cliente) {
        super();
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
        this.complemento = complemento;
        this.estado = estado;
        this.cliente = cliente;
    }


    public Integer getId() {
        return id;
    }


    public String getCep() {
        return cep;
    }


    public void setCep(String cep) {
        this.cep = cep;
    }


    public String getRua() {
        return rua;
    }


    public void setRua(String rua) {
        this.rua = rua;
    }


    public String getBairro() {
        return bairro;
    }


    public void setBairro(String bairro) {
        this.bairro = bairro;
    }


    public String getCidade() {
        return cidade;
    }


    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public String getNumero() {
        return numero;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }


    public String getComplemento() {
        return complemento;
    }


    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    public ClienteModel getCliente() {
        return cliente;
    }


    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
}