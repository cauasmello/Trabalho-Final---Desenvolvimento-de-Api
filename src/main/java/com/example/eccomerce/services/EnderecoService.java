package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.EnderecoModel;
import com.example.eccomerce.models.ViaCepModel;
import com.example.eccomerce.repositories.EnderecoRepository;
import com.example.eccomerce.resources.ViaCepResource;
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

    public List<EnderecoModel> getAll() {
        List<EnderecoModel> list = repository.findAll();
        return list;
    }

    public EnderecoModel get(Integer id) throws ErrorException {
        Optional<EnderecoModel> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ErrorException("Endereço não existe!");
        }
        return optional.get();
    }

    public String add(ViaCepModel viacep) throws ErrorException {
        if(viacep.getCep() == null){
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

        EnderecoModel endereco = new EnderecoModel(buscarEndereco, null);

        repository.save(endereco);
        return "Criado com sucesso!";
    }

    public String put(EnderecoModel enderecoNew, Integer id) throws ErrorException {
        Optional<EnderecoModel> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ErrorException("Endereço não existe!");
        }
        EnderecoModel endereco = optional.get();

        if (enderecoNew.getCep() != null) {
            endereco.setCep(enderecoNew.getCep());
        }

        if (enderecoNew.getRua() != null && !enderecoNew.getRua().equals("")) {
            endereco.setRua(enderecoNew.getRua());
        }

        if (enderecoNew.getBairro() != null && !enderecoNew.getBairro().equals("")) {
            endereco.setBairro(enderecoNew.getBairro());
        }

        if (enderecoNew.getCidade() != null && !enderecoNew.getCidade().equals("")) {
            endereco.setCidade(enderecoNew.getCidade());
        }

        if (enderecoNew.getNumero() != null) {
            endereco.setNumero(enderecoNew.getNumero());
        }

        if (enderecoNew.getComplemento() != null && !enderecoNew.getComplemento().equals("")) {
            endereco.setComplemento(enderecoNew.getComplemento());
        }

        if (enderecoNew.getEstado() != null && !enderecoNew.getEstado().equals("")) {
            endereco.setEstado(enderecoNew.getEstado());
        }

        return "Endereço Atualizado";

    }

    public String delete(Integer id) throws ErrorException {
        Optional<EnderecoModel> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ErrorException("Endereço não existe!");
        }
        repository.deleteById(id);
        return "Deletado com sucesso";
    }

}
