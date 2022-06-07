package com.example.eccomerce.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produtos_img")
public class ImagemProdutoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column(name = "nome")
	private String name;

	@NotNull
	@Column(name = "mimeType")
	private String mimeType;

	@NotNull
	@Lob
	private byte[] data;
	
	@OneToOne()
	@JoinColumn(name = "produto_id", referencedColumnName = "id")
	private ProdutoModel produto;

	public ImagemProdutoModel() {
		super();
	}

	public ImagemProdutoModel(Integer id, String name, String mimeType, byte[] data, ProdutoModel produto) {
		super();
		this.id = id;
		this.name = name;
		this.mimeType = mimeType;
		this.data = data;
		this.produto = produto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public ProdutoModel getProduto() {
		return produto;
	}

	public void setProduto(ProdutoModel produto) {
		this.produto = produto;
	}
	
	

	
}
