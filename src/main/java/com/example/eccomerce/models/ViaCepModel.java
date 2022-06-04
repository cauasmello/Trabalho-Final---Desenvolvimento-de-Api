package com.example.eccomerce.models;

public class ViaCepModel {
	
	private String cep;
	private String logradouro;
	private String bairro;
	private String numero;
	private String complemento;
	private String localidade;
	private String uf;

	
	public ViaCepModel() {
		super();
	}

	public ViaCepModel(String cep, String logradouro, String bairro, String numero, String complemento, String localidade, String uf) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.numero = numero;
		this.complemento = complemento;
		this.localidade = localidade;
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep){
		this.cep = cep;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
