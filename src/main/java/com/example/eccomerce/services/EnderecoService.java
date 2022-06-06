package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.ClienteModel;
import com.example.eccomerce.models.EnderecoModel;
import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.models.ViaCepModel;
import com.example.eccomerce.repositories.EnderecoRepository;
import com.example.eccomerce.resources.ToolsResource;
import com.example.eccomerce.resources.ViaCepResource;
import com.example.eccomerce.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository repository;

    @Autowired
    ViaCepResource viaCep;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ToolsResource tools;

    public List<EnderecoModel> getAll(String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        ClienteModel clienteModel = myUser.getCliente();
        tools.existCliente(clienteModel);

        return clienteModel.getEnderecos();
    }

    public EnderecoModel get(Integer id, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        ClienteModel clienteModel = myUser.getCliente();
        tools.existCliente(clienteModel);

        Optional<EnderecoModel> optional = repository.findByClienteAndID(clienteModel, id);
        if (optional.isEmpty()) {
            throw new ErrorException("Endereço não existe!");
        }

        return optional.get();
    }

    public Void add(ViaCepModel viacep, String token) throws ErrorException, IllegalAccessException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        ClienteModel clienteModel = myUser.getCliente();
        tools.existCliente(clienteModel);

        if(repository.existCEP(Long.parseLong(viacep.getCep())) > 0){
            throw new ErrorException("Endereço ja existe");
        }

        if(viacep.getCep() == null || String.valueOf(viacep.getCep()).length() != 8){
            throw new ErrorException("CEP Invalido");
        }

        if(viacep.getNumero() == null){
            throw new ErrorException("Numero Invalido");
        }

        if(viacep.getComplemento() == null){
            throw new ErrorException("Complemento Invalido");
        }

        ViaCepModel buscarEndereco = viaCep.getViaCep(viacep.getCep());
        buscarEndereco.setNumero(viacep.getNumero());
        buscarEndereco.setComplemento(viacep.getComplemento());
        buscarEndereco.setCep(viacep.getCep());

        if(buscarEndereco.isNull()){
            throw new ErrorException("CEP invalido");
        }

        EnderecoModel endereco = new EnderecoModel(buscarEndereco, clienteModel);

        repository.save(endereco);
        return null;
    }

    public Void put(ViaCepModel viacep, Integer id, String token) throws ErrorException, IllegalAccessException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        ClienteModel clienteModel = myUser.getCliente();
        tools.existCliente(clienteModel);

        Optional<EnderecoModel> optional = repository.findByClienteAndID(clienteModel, id);

        if (optional.isEmpty()) {
            throw new ErrorException("Endereço não existe!");
        }

        if(repository.existCEP(Long.parseLong(viacep.getCep())) > 0){
            throw new ErrorException("Endereço ja existe");
        }

        if(viacep.getCep() == null || String.valueOf(viacep.getCep()).length() != 8){
            throw new ErrorException("CEP Invalido");
        }

        if(viacep.getNumero() == null){
            throw new ErrorException("Numero Invalido");
        }

        if(viacep.getComplemento() == null){
            throw new ErrorException("Complemento Invalido");
        }

        EnderecoModel endereco = optional.get();

        ViaCepModel buscarEndereco = viaCep.getViaCep(viacep.getCep());
        buscarEndereco.setNumero(viacep.getNumero());
        buscarEndereco.setComplemento(viacep.getComplemento());
        buscarEndereco.setCep(viacep.getCep());

        if(buscarEndereco.isNull()){
            throw new ErrorException("CEP invalido");
        }

        endereco.update(buscarEndereco);

        repository.save(endereco);
        return null;
    }

    public Void delete(Integer id, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        ClienteModel clienteModel = myUser.getCliente();
        tools.existCliente(clienteModel);

        Optional<EnderecoModel> optional = repository.findByClienteAndID(clienteModel, id);
        if (optional.isEmpty()) {
            throw new ErrorException("Endereço não existe!");
        }
        EnderecoModel endereco = optional.get();

        repository.deleteById(endereco.getId());
        return null;
    }

}
