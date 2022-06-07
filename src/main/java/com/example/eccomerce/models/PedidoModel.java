package com.example.eccomerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "pedidos")
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "numero", unique = true)
    private String numero;

    @NotNull
    @Column(name = "valor")
    private Double valor;

    @NotNull
    @Column(name = "criado")
    private LocalDate criado;

    @Column(name = "entrege")
    private LocalDate entrege;

    @NotNull
    @Column(name = "status")
    private String status;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteModel cliente;

    @JsonIgnore
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

    public PedidoModel(ClienteModel cliente) {
        super();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String now = formatter.format(LocalDate.now());

        this.numero = this.generateNumber();
        this.valor = 0.00;
        this.criado = LocalDate.parse(now, formatter);
        this.entrege = null;
        this.status = "Aberto";
        this.cliente = cliente;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String now = formatter.format(entrege);
        this.entrege = LocalDate.parse(now, formatter);
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

    private String generateNumber() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
