package com.example.eccomerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

    @Column(name = "criado")
    private LocalDate criado;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private CategoriaModel categoria;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    private FuncionarioModel funcionario;

    @JsonIgnore
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProdutoModel> pedidos;

    @JsonIgnore
    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemProdutoModel imagem;

    public ProdutoModel() {
        super();
    }

    public ProdutoModel(Integer id, String nome, String descricao, Double valor, Integer quantidade_estoque, LocalDate criado, CategoriaModel categoria, FuncionarioModel funcionario, List<PedidoProdutoModel> pedidos, ImagemProdutoModel imagem) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade_estoque = quantidade_estoque;
		this.criado = criado;
		this.categoria = categoria;
		this.funcionario = funcionario;
		this.pedidos = pedidos;
		this.imagem = imagem;
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

    public LocalDate getCriado() {
		return criado;
	}

	public void setCriado(LocalDate criado) {
		this.criado = criado;
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

	public ImagemProdutoModel getImagem() {
		return imagem;
	}

	public void setImagem(ImagemProdutoModel imagem) {
		this.imagem = imagem;
	}

}
