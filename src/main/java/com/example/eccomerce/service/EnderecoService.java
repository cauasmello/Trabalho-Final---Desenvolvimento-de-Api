package com.example.eccomerce.service;

import com.example.eccomerce.exception.EndereçoExisteException;
import com.example.eccomerce.exception.EndereçoNotException;
import com.example.eccomerce.model.EnderecoModel;
import com.example.eccomerce.model.ViaCepDTOModel;
import com.example.eccomerce.repository.EnderecoRepository;
import com.example.eccomerce.restclient.RestViaCep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository repositorio;

    @Autowired
    RestViaCep viaCep;

    public List<EnderecoModel> listaTudo() {
        return repositorio.findAll();
    }

    public EnderecoModel listaEnderecos(Integer id) throws EndereçoNotException {
        Optional<EnderecoModel> optional = repositorio.findById(id);
        if (optional.isEmpty()) {
            throw new EndereçoNotException("Endereço não existe");
        }
        return optional.get();
    }

    public void verificaEnderecoExiste(EnderecoModel endereco) throws EndereçoExisteException {
        Optional<EnderecoModel> optional = repositorio.findById(endereco.getId());
        if (optional.isPresent()) {
            throw new EndereçoExisteException("Livro já existe");
        }

    }

    public EnderecoModel inserir(EnderecoModel endereco) throws EndereçoExisteException {
        ViaCepDTOModel novoEndereco = viaCep.getViaCep(endereco.getCep());
        endereco.setRua(novoEndereco.getLogradouro());
        endereco.setCidade(novoEndereco.getLocalidade());
        endereco.setEstado(novoEndereco.getUf());
        endereco.setBairro(novoEndereco.getBairro());

        return repositorio.save(endereco);
    }

    public EnderecoModel atualizarPorId(EnderecoModel endereco, Integer id) throws EndereçoExisteException, EndereçoNotException {
        Optional<EnderecoModel> optional = repositorio.findById(id);
        if (optional.isEmpty()) {
            throw new EndereçoNotException("Endereço não existe");
        }
        EnderecoModel enderecoOld = optional.get();

        if (endereco.getId() != null) {
            verificaEnderecoExiste(endereco);
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
            enderecoOld.setCidade(endereco.getCidade());
        }

        if (endereco.getNumero() != null && !endereco.getNumero().equals("")) {
            enderecoOld.setNumero(endereco.getNumero());
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
        Optional<EnderecoModel> optional = repositorio.findById(id);
        if (optional.isEmpty()) {
            throw new EndereçoNotException("Endereço não existe");
        }
        repositorio.deleteById(id);
        return "Deletado com sucesso";
    }

}
