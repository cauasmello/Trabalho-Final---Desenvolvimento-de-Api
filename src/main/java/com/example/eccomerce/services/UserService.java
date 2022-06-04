package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.GeneralException;
import com.example.eccomerce.models.*;
import com.example.eccomerce.repositories.ClienteRepository;
import com.example.eccomerce.repositories.EnderecoRepository;
import com.example.eccomerce.repositories.FuncionarioRepository;
import com.example.eccomerce.repositories.UserRepository;
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

    public List<UserModel> getAllCliente() {
        return userRepository.findByRole(0);
    }

    public List<UserModel> getAllFuncionario() {
        return userRepository.findByRole(1);
    }

    public String createCliente(ClienteDTOModel user) throws GeneralException {
        try {
            if(user.isNull()){
                throw new GeneralException("Parametros invalido");
            }
        } catch (Exception e) {
            throw new GeneralException("Parametros invalido");
        }

        Integer existUser = userRepository.existUser(user.getEmail(), user.getUsername());
        if (existUser > 0) {
            throw new GeneralException("Usuario já existe");
        }
        UserModel userModel = new UserModel(user);

        ClienteModel clienteModel = new ClienteModel(user, userModel);

        EnderecoModel enderecoModel = new EnderecoModel(user, clienteModel);

        userRepository.save(userModel);
        clienteRepository.save(clienteModel);
        enderecoRepository.save(enderecoModel);
        return "Criado com sucesso!";
    }

    public String createFuncionario(FuncionarioDTOModel user) throws GeneralException {
        try {
            if(user.isNull()){
                throw new GeneralException("Parametros invalido");
            }
        } catch (Exception e) {
            throw new GeneralException("Parametros invalido");
        }

        Integer existUser = userRepository.existUser(user.getEmail(), user.getUsername());
        if(existUser > 0){
            throw new GeneralException("Usuario já existe");
        }

        UserModel userModel = new UserModel(user);

        FuncionarioModel funcionarioModel = new FuncionarioModel(user, userModel);

        userRepository.save(userModel);
        funcionarioRepository.save(funcionarioModel);
        return "Criado com sucesso!";
    }

    public String login(UserModel user) {
        return "Logado com sucesso";
    }
}
