package com.example.eccomerce.models;

public class ViaCepModel {
	
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;

	
	public ViaCepModel() {
		super();
	}

	public ViaCepModel(String cep, String logradouro, String bairro, String localidade, String uf) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public String getUf() {
		return uf;
	}

}
