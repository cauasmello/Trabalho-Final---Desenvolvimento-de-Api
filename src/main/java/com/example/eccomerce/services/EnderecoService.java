package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.GeneralException;
import com.example.eccomerce.models.EnderecoModel;
import com.example.eccomerce.models.ViaCepModel;
import com.example.eccomerce.repositories.EnderecoRepository;
import com.example.eccomerce.resources.ViaCepResource;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository repositorio;

    @Autowired
    ViaCepResource viaCep;

    public List<EnderecoModel> getAll() {
        List<EnderecoModel> list = repositorio.findAll();
        System.out.println(list);
        return list;
    }

    public EnderecoModel get(Integer id) throws GeneralException {
        Optional<EnderecoModel> optional = repositorio.findById(id);
        if (optional.isEmpty()) {
            throw new GeneralException("Endereço não existe!");
        }
        return optional.get();
    }

    public String add(@NotNull EnderecoModel endereco) throws GeneralException {
        if(endereco.getId() != null){
            Optional<EnderecoModel> optional = repositorio.findById(endereco.getId());
            if (optional.isPresent()) {
                throw new GeneralException("Endereço já existe!");
            }
        }

        ViaCepModel novoEndereco = viaCep.getViaCep(endereco.getCep());
        endereco.setRua(novoEndereco.getLogradouro());
        endereco.setCidade(novoEndereco.getLocalidade());
        endereco.setEstado(novoEndereco.getUf());
        endereco.setBairro(novoEndereco.getBairro());

        repositorio.save(endereco);
        return "Criado com sucesso!";
    }

    public String put(EnderecoModel enderecoNew, Integer id) throws GeneralException {
        Optional<EnderecoModel> optional = repositorio.findById(id);
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
        Optional<EnderecoModel> optional = repositorio.findById(id);
        if (optional.isEmpty()) {
            throw new GeneralException("Endereço não existe!");
        }
        repositorio.deleteById(id);
        return "Deletado com sucesso";
    }

}
