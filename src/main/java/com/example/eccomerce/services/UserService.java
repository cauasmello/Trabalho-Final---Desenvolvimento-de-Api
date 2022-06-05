package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.*;
import com.example.eccomerce.repositories.ClienteRepository;
import com.example.eccomerce.repositories.EnderecoRepository;
import com.example.eccomerce.repositories.FuncionarioRepository;
import com.example.eccomerce.repositories.UserRepository;
import com.example.eccomerce.resources.ViaCepResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ViaCepResource viaCep;

    public List<UserModel> getAllCliente() {
        return userRepository.findByRole(0);
    }

    public List<UserModel> getAllFuncionario() {
        return userRepository.findByRole(1);
    }

    public Void createCliente(ClienteDTOModel user) throws ErrorException {
        if(user.getCep() != null){
            ViaCepModel viaCepModel = this.viaCep.getViaCep(user.getCep().toString());
            user.setViaCep(viaCepModel);
        }

        try {
            if(user.isNull()){
                throw new ErrorException("Parametros invalido");
            }
        } catch (Exception e) {
            throw new ErrorException("Parametros invalido");
        }

        Integer existUser = userRepository.existUser(user.getEmail(), user.getUsername());
        if (existUser > 0) {
            throw new ErrorException("Usuario já existe");
        }
        UserModel userModel = new UserModel(user);

        ClienteModel clienteModel = new ClienteModel(user, userModel);

        EnderecoModel enderecoModel = new EnderecoModel(user, clienteModel);

        userRepository.save(userModel);
        clienteRepository.save(clienteModel);
        enderecoRepository.save(enderecoModel);

        return null;
    }

    public Void createFuncionario(FuncionarioDTOModel user) throws ErrorException {
        try {
            if(user.isNull()){
                throw new ErrorException("Parametros invalido");
            }
        } catch (Exception e) {
            throw new ErrorException("Parametros invalido");
        }

        Integer existUser = userRepository.existUser(user.getEmail(), user.getUsername());
        if(existUser > 0){
            throw new ErrorException("Usuario já existe");
        }

        UserModel userModel = new UserModel(user);

        FuncionarioModel funcionarioModel = new FuncionarioModel(user, userModel);

        userRepository.save(userModel);
        funcionarioRepository.save(funcionarioModel);

        return null;
    }

    public Void login(UserModel user) {
        return null;
    }
}
