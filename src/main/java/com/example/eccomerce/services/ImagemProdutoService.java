package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.ImagemProdutoModel;
import com.example.eccomerce.models.ProdutoModel;
import com.example.eccomerce.repositories.ImagemProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Service
public class ImagemProdutoService {

	@Autowired
	ImagemProdutoRepository repository;
	
	@Transactional
	public void add(ProdutoModel produto, MultipartFile file) throws IOException {
		ImagemProdutoModel image = new ImagemProdutoModel();
		image.setMimeType(file.getContentType());
		image.setName(file.getName());
		image.setData(file.getBytes());
		image.setProduto(produto);
		repository.save(image);
	}

	public String generateURL(Integer id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("produto/{id}/image").buildAndExpand(id).toUri();
		return uri.toString();
	}
	
	@Transactional
	public ImagemProdutoModel getImagem(Integer id) throws ErrorException {
		Optional<ImagemProdutoModel> optional = repository.findById(id);
		if(optional.isEmpty()) {
			throw new ErrorException("Imagem não existe!");
		}
		return optional.get();
	}

}
