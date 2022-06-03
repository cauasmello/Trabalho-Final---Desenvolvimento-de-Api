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
    EnderecoRepository repositorio;

    @Autowired
    ViaCepResource viaCep;

    public List<EnderecoModel> getAll() throws GeneralException {
        try{
            List<EnderecoModel> list = repositorio.findAll();
            if (list.isEmpty()) {
                throw new GeneralException("Nenhum endereço encontrado!");
            }
            return list;
        }catch (Exception e){
            throw new GeneralException("Não foi possivel retornar endereço!");
        }
    }

    public EnderecoModel get(Integer id) throws GeneralException {
        try{
            Optional<EnderecoModel> optional = repositorio.findById(id);
            if (optional.isEmpty()) {
                throw new GeneralException("Endereço não existe");
            }
            return optional.get();
        }catch (Exception e){
            throw new GeneralException("Não foi possivel retornar endereço!");
        }
    }

    public EnderecoModel add(EnderecoModel endereco) throws GeneralException {
        try{
            Optional<EnderecoModel> optional = repositorio.findById(endereco.getId());
            if (optional.isPresent()) {
                throw new GeneralException("Endereço já existe");
            }

            ViaCepModel novoEndereco = viaCep.getViaCep(endereco.getCep());
            endereco.setRua(novoEndereco.getLogradouro());
            endereco.setCidade(novoEndereco.getLocalidade());
            endereco.setEstado(novoEndereco.getUf());
            endereco.setBairro(novoEndereco.getBairro());

            return repositorio.save(endereco);
        }catch (Exception e){
            throw new GeneralException("Não foi possivel adcionar!");
        }
    }

    public EnderecoModel put(EnderecoModel enderecoNew, Integer id) throws GeneralException {
        try{
            Optional<EnderecoModel> optional = repositorio.findById(id);
            if (optional.isEmpty()) {
                throw new GeneralException("Endereço não existe");
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

            return endereco;
        }catch (Exception e){
            throw new GeneralException("Não foi possivel atualizar!");
        }

    }

    public String delete(Integer id) throws GeneralException {
        try{
            Optional<EnderecoModel> optional = repositorio.findById(id);
            if (optional.isEmpty()) {
                throw new GeneralException("Endereço não existe");
            }
            repositorio.deleteById(id);
            return "Deletado com sucesso";
        }catch (Exception e){
            throw new GeneralException("Não foi possivel deletar!");
        }
    }

}
