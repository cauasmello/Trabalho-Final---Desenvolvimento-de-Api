package com.example.eccomerce.services;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eccomerce.exceptions.GeneralException;
import com.example.eccomerce.models.ClienteModel;
import com.example.eccomerce.models.EnderecoModel;
import com.example.eccomerce.models.ViaCepModel;
import com.example.eccomerce.repositories.ClienteRepository;
import com.example.eccomerce.repositories.EnderecoRepository;
import com.example.eccomerce.resources.ViaCepResource;

@Service
public class ClienteService {
		@Autowired
	    ClienteRepository repositorio;

	    @Autowired
	    ViaCepResource viaId;

	    public List<ClienteModel> getAll() {
	        List<ClienteModel> list = repositorio.findAll();
	        System.out.println(list);
	        return list;
}
	    public ClienteModel get(Integer id) throws GeneralException {
	        Optional<ClienteModel> optional = repositorio.findById(id);
	        if (optional.isEmpty()) {
	            throw new GeneralException("Cliente não existe!");
	        }
	        return optional.get();
	    }
	    
	    public String add(@NotNull ClienteModel cliente) throws GeneralException {
	        if(cliente.getId() != null){
	        	Optional<ClienteModel> optional = repositorio.findById(cliente.getId());	           
	        	if (optional.isPresent()) {
	                throw new GeneralException("Cliente já existe!");
	            }
	        }
	        
	        repositorio.save(cliente);
	        return "Criado com sucesso!";
	    }
	        	
	        	 public String put(ClienteModel clienteNew, Integer id) throws GeneralException {
	        	        Optional<ClienteModel> optional = repositorio.findById(id);
	        	        if (optional.isEmpty()) {
	        	            throw new GeneralException("Cliente não existe!");
	        	        }
	        	        ClienteModel cliente = optional.get();

	        	        if (clienteNew.getNome() != null && !clienteNew.getNome().equals("")) {
	        	            cliente.setNome(clienteNew.getNome());
	        	        }

	        	        if (clienteNew.getCpf() != null && !clienteNew.getCpf().equals("")) {
	        	            cliente.setCpf(clienteNew.getCpf());
	        	        }

	        	        if (clienteNew.getTelefone() != null && !clienteNew.getTelefone().equals("")) {
	        	            cliente.setTelefone(clienteNew.getTelefone());
	        	        }

	        	        if (clienteNew.getNascimento() != null && !clienteNew.getNascimento().equals("")) {
	        	            cliente.setNascimento(clienteNew.getNascimento());
	        	        }

	        	        if (clienteNew.getUser() != null && !clienteNew.getUser().equals("")) {
	        	            cliente.setUser(clienteNew.getUser());
	        	        }

	        	        return "Cliente Atualizado";
	        	 }
	        	 
	        	 public String delete(Integer id) throws GeneralException {
	        	        Optional<ClienteModel> optional = repositorio.findById(id);
	        	        if (optional.isEmpty()) {
	        	            throw new GeneralException("Cliente não existe!");
	        	        }
	        	        repositorio.deleteById(id);
	        	        return "Deletado com sucesso";
	        	    }
	        }
	          
