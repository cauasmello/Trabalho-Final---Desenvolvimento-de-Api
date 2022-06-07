package com.example.eccomerce.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

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
    @PastOrPresent
    private LocalDate criado;

    @NotNull
    @Column(name = "img")
    private String img;

    @ManyToOne()
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private CategoriaModel categoria;

    @ManyToOne()
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    private FuncionarioModel funcionario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProdutoModel> pedidos;
    
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable=false)
	private ImagemProdutoModel imagem;

    public ProdutoModel() {
        super();
    }

   

    public ProdutoModel(Integer id, @NotNull String nome, @NotNull String descricao, @NotNull Double valor,
			@NotNull Integer quantidade_estoque, @NotNull LocalDate criado, @NotNull String img, CategoriaModel categoria,
			FuncionarioModel funcionario, List<PedidoProdutoModel> pedidos, ImagemProdutoModel imagem) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade_estoque = quantidade_estoque;
		this.criado = criado;
		this.img = img;
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



	public ImagemProdutoModel getImagem() {
		return imagem;
	}



	public void setImagem(ImagemProdutoModel imagem) {
		this.imagem = imagem;
	}
    
    
}
