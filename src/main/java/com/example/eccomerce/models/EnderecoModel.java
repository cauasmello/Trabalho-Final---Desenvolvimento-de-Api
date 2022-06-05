package com.example.eccomerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;

import javax.persistence.*;


@Entity
@Table(name = "enderecos")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "cep")
    private Long cep;

    @NotNull
    @Column(name = "rua")
    private String rua;

    @NotNull
    @Column(name = "bairro")
    private String bairro;

    @NotNull
    @Column(name = "cidade")
    private String cidade;

    @NotNull
    @Column(name = "numero")
    private Integer numero;

    @NotNull
    @Column(name = "complemento")
    private String complemento;

    @NotNull
    @Column(name = "estado")
    private String estado;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteModel cliente;

    public EnderecoModel() {
        super();
    }

    public EnderecoModel(Integer id, Long cep, String rua, String bairro, String cidade, Integer numero, String complemento, String estado, ClienteModel cliente) {
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

    public EnderecoModel(ViaCepModel viaCep, ClienteModel cliente) {
        super();
        this.cep = Long.parseLong(viaCep.getCep());
        this.rua = viaCep.getLogradouro();
        this.bairro = viaCep.getBairro();
        this.cidade = viaCep.getLocalidade();
        this.numero = Integer.parseInt(viaCep.getNumero());
        this.complemento = viaCep.getComplemento();
        this.estado = viaCep.getUf();
        this.cliente = cliente;
    }

    public EnderecoModel(ClienteDTOModel clienteDTO, ClienteModel cliente) {
        super();
        this.cep = clienteDTO.getCep();
        this.rua = clienteDTO.getRua();
        this.bairro = clienteDTO.getBairro();
        this.cidade = clienteDTO.getCidade();
        this.numero = clienteDTO.getNumero();
        this.complemento = clienteDTO.getComplemento();
        this.estado = clienteDTO.getEstado();
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }


    public Long getCep() {
        return cep;
    }


    public void setCep(Long cep) {
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


    public Integer getNumero() {
        return numero;
    }


    public void setNumero(Integer numero) {
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