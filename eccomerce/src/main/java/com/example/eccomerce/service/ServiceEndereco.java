package com.example.eccomerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eccomerce.exception.EndereçoExisteException;
import com.example.eccomerce.exception.EndereçoNotException;
import com.example.eccomerce.model.Endereco;
import com.example.eccomerce.model.ViaCepDTO;
import com.example.eccomerce.repository.RepositorioEndereco;
import com.example.eccomerce.restclient.RestViaCep;



@Service
public class ServiceEndereco {
	@Autowired
	RepositorioEndereco repositorio;
	
	@Autowired
	RestViaCep viaCep;
	
	public List<Endereco> listaTudo() {
		return repositorio.findAll();
	}

	public Endereco listaEnderecos(Integer id) throws EndereçoNotException {
		Optional<Endereco> optional = repositorio.findById(id);
		if (optional.isEmpty()) {
			throw new EndereçoNotException("Endereço não existe");
		}
		return optional.get();
	}

	public void verificaEndereçoExiste(Endereco endereco) throws EndereçoExisteException {
		Optional<Endereco> optional = repositorio.findById(endereco.getId());
		if (optional.isPresent()) {
			throw new EndereçoExisteException("Livro já existe");
	}
		
	}
	
	public Endereco inserir(Endereco endereco) throws EndereçoExisteException {
		ViaCepDTO novoEndereco = viaCep.getViaCep(endereco.getCep());
		endereco.setRua(novoEndereco.getLogradouro());
		endereco.setCidade(novoEndereco.getLocalidade());
		endereco.setEstado(novoEndereco.getUf());
		endereco.setBairro(novoEndereco.getBairro());
		      
		return repositorio.save(endereco) ;
	}

	public Endereco atualizarPorId(Endereco endereco, Integer id) throws EndereçoExisteException, EndereçoNotException {
		Optional<Endereco> optional = repositorio.findById(id);
		if (optional.isEmpty()) {
			throw new EndereçoNotException("Endereço não existe");
		}
		Endereco enderecoOld = optional.get();
		
		if ( endereco.getId() != null) {
			verificaEndereçoExiste(endereco);
			enderecoOld.setId(endereco.getId());
		}

			if (endereco.getCep() != null && !endereco.getCep().equals("")) {
				enderecoOld.setCep(endereco.getCep());
				}
			
			if (endereco.getRua() != null && !endereco.getRua().equals("")) {
				enderecoOld.setRua(endereco.getRua());
				}
			
			if (endereco.getBairro() != null && !endereco.getBairro().equals("")) {
				enderecoOld.setBairro(endereco.getBairro());
				}
			
			if (endereco.getCidade() != null && !endereco.getCidade().equals("")) {
				enderecoOld.setCep(endereco.getCidade());
				}
			
			if (endereco.getNumero() != null && !endereco.getNumero().equals("")) {
				enderecoOld.setCep(endereco.getNumero());
				}
			
			if (endereco.getComplemento() != null && !endereco.getComplemento().equals("")) {
				enderecoOld.setComplemento(endereco.getComplemento());
				}
			
			if (endereco.getEstado() != null && !endereco.getEstado().equals("")) {
				enderecoOld.setEstado(endereco.getEstado());
				}
			
			return enderecoOld;
		}
	
	public String removerPorId(Integer id) throws EndereçoNotException {
		Optional<Endereco> optional = repositorio.findById(id);
		if (optional.isEmpty()) {
			throw new EndereçoNotException("Endereço não existe");
		}
		repositorio.deleteById(id);
		return "Deletado com sucesso";
	}


	
	

}
