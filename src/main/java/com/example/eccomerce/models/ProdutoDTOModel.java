package com.example.eccomerce.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;

public class ProdutoDTOModel {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Double valor;
	private String url;
	private CategoriaModel categoria;
	private Integer quantidade_estoque;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer categoria_id;
	
	public ProdutoDTOModel() {
		super();
	}

	public ProdutoDTOModel(ProdutoModel produto, String url) {
		super();
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.quantidade_estoque = produto.getQuantidade_estoque();
		this.categoria = produto.getCategorio();
		this.valor = produto.getValor();
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	public Integer getQuantidade_estoque() {
		return quantidade_estoque;
	}

	public void setQuantidade_estoque(Integer quantidade_estoque) {
		this.quantidade_estoque = quantidade_estoque;
	}

	public Integer getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(Integer categoria_id) {
		this.categoria_id = categoria_id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Boolean isNull() throws IllegalAccessException {
		for (Field f : getClass().getDeclaredFields()) {
			if(f.get(this) == null) return true;
		}
		return false;
	}
}
