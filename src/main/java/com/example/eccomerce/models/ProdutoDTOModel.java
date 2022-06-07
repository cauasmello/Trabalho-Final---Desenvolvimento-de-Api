package com.example.eccomerce.models;

public class ProdutoDTOModel {
	
	private Integer id;
	private String nome;
	private String url;
	
	public ProdutoDTOModel() {
		super();
	}

	public ProdutoDTOModel(Integer id, String nome, String url) {
		super();
		this.id = id;
		this.nome = nome;
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


	
	

}
