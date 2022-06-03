package com.example.eccomerce.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero;

    private Double valor;

    private Date criado;

    private Date entrege;

    private String status;

    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteModel cliente;

    public PedidoModel() {
        super();
    }

    public PedidoModel(Integer id, String numero, Double valor, Date criado, Date entrege, String status, ClienteModel cliente) {
        super();
        this.id = id;
        this.numero = numero;
        this.valor = valor;
        this.criado = criado;
        this.entrege = entrege;
        this.status = status;
        this.cliente = cliente;
    }

}
