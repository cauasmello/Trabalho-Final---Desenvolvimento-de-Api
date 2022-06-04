package com.example.eccomerce.models;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "produtos")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Column(name = "valor")
    private Double valor;

    @NotNull
    @Column(name = "quantidade_estoque")
    private Integer quantidade_estoque;

    @NotNull
    @Column(name = "criado")
    private Date criado;

    @NotNull
    @Column(name = "img")
    private String img;

    @ManyToOne()
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private CategoriaModel categoria;

    @ManyToOne()
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    private FuncionarioModel funcionario;

    @OneToMany(mappedBy = "produto")
    private List<PedidoProdutoModel> pedidos;

    public ProdutoModel() {
        super();
    }

    public ProdutoModel(String nome, String descricao, Double valor, Integer quantidade_estoque, String img, CategoriaModel categoria, List<PedidoProdutoModel> pedidos, FuncionarioModel funcionario) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade_estoque = quantidade_estoque;
        this.img = img;
        this.categoria = categoria;
        this.funcionario = funcionario;
        this.pedidos = pedidos;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(Integer quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public Date getCriado() {
        return criado;
    }

    public void setCriado(Date criado) {
        this.criado = criado;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public CategoriaModel getCategorio() {
        return categoria;
    }

    public void setCategorio(CategoriaModel categorio) {
        this.categoria = categorio;
    }

    public FuncionarioModel getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioModel funcionario) {
        this.funcionario = funcionario;
    }

    public List<PedidoProdutoModel> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoProdutoModel> pedidos) {
        this.pedidos = pedidos;
    }
}
