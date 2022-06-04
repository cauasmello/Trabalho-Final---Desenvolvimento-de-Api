package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.GeneralException;
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

    public EnderecoModel get(Integer id) throws GeneralException {
        Optional<EnderecoModel> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new GeneralException("Endereço não existe!");
        }
        return optional.get();
    }

    public String add(ViaCepModel viacep) throws GeneralException {
        if(viacep.getCep() == null){
            throw new GeneralException("CEP Invalido");
        }

        if(viacep.getNumero() == null){
            throw new GeneralException("Numero Invalido");
        }

        if(viacep.getComplemento() == null){
            throw new GeneralException("Complemento Invalido");
        }

        ViaCepModel buscarEndereco = viaCep.getViaCep(viacep.getCep());
        buscarEndereco.setNumero(viacep.getNumero());
        buscarEndereco.setComplemento(viacep.getComplemento());
        buscarEndereco.setCep(viacep.getCep());

        EnderecoModel endereco = new EnderecoModel(buscarEndereco, null);

        repository.save(endereco);
        return "Criado com sucesso!";
    }

    public String put(EnderecoModel enderecoNew, Integer id) throws GeneralException {
        Optional<EnderecoModel> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new GeneralException("Endereço não existe!");
        }
        EnderecoModel endereco = optional.get();

        if (enderecoNew.getCep() != null && !enderecoNew.getCep().equals("")) {
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

        if (enderecoNew.getNumero() != null && !enderecoNew.getNumero().equals("")) {
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

    public String delete(Integer id) throws GeneralException {
        Optional<EnderecoModel> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new GeneralException("Endereço não existe!");
        }
        repository.deleteById(id);
        return "Deletado com sucesso";
    }

}
