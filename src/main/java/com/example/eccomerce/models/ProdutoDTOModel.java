package com.example.eccomerce.models;

public class ProdutoDTOModel {
	
	private Integer id;
	private String nome;
	private String descricao;
	private String url;
	
	public ProdutoDTOModel() {
		super();
	}

	public ProdutoDTOModel(ProdutoModel produto, String url) {
		super();
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
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
}
