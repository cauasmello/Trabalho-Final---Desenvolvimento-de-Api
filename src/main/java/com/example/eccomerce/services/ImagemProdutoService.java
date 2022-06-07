package com.example.eccomerce.services;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.eccomerce.models.ImagemProdutoModel;
import com.example.eccomerce.models.ProdutoModel;
import com.example.eccomerce.repositories.ImagemProdutoRepository;

@Service
public class ImagemProdutoService {

	@Autowired
	ImagemProdutoRepository repository;
	
	@Transactional
	public ImagemProdutoModel add(ProdutoModel produto, MultipartFile file) throws IOException {
		ImagemProdutoModel image = new ImagemProdutoModel();
		image.setMimeType(file.getContentType());
		image.setName(file.getName());
		image.setData(file.getBytes());
		image.setProduto(produto);
		return repository.save(image);
	}

	public String generateURL(Integer id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("produto/{id}/image").buildAndExpand(id).toUri();
		return uri.toString();
	}
	
	@Transactional
	public ImagemProdutoModel getImagem(Integer id) {
		Optional<ImagemProdutoModel> optional = repository.findById(id);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}
}
