package com.example.eccomerce.models;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "numero")
    private String numero;

    @NotNull
    @Column(name = "valor")
    private Double valor;

    @NotNull
    @Column(name = "criado")
    private LocalDate criado;

    @NotNull
    @Column(name = "entrege")
    private LocalDate entrege;

    @NotNull
    @Column(name = "status")
    private String status;

    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteModel cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProdutoModel> produtos;

    public PedidoModel() {
        super();
    }

    public PedidoModel(Integer id, String numero, Double valor, LocalDate criado, LocalDate entrege, String status, ClienteModel cliente, List<PedidoProdutoModel> produtos) {
        super();
        this.id = id;
        this.numero = numero;
        this.valor = valor;
        this.criado = criado;
        this.entrege = entrege;
        this.status = status;
        this.cliente = cliente;
        this.produtos = produtos;
    }

    public Integer getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getCriado() {
        return criado;
    }

    public void setCriado(LocalDate criado) {
        this.criado = criado;
    }

    public LocalDate getEntrege() {
        return entrege;
    }

    public void setEntrege(LocalDate entrege) {
        this.entrege = entrege;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public List<PedidoProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<PedidoProdutoModel> produtos) {
        this.produtos = produtos;
    }
}
