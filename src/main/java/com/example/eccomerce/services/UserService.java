package com.example.eccomerce.services;

import com.example.eccomerce.resources.ToolsResource;
import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.*;
import com.example.eccomerce.repositories.ClienteRepository;
import com.example.eccomerce.repositories.EnderecoRepository;
import com.example.eccomerce.repositories.FuncionarioRepository;
import com.example.eccomerce.repositories.UserRepository;
import com.example.eccomerce.resources.ViaCepResource;
import com.example.eccomerce.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    BCryptPasswordEncoder bCrypt;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ToolsResource tools;

    public UserModel getMyData(String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);

        return myUser;
    }

    public Void updateMyData(UserModel user, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);

        Integer existUser = userRepository.existUser(user.getEmail(), user.getUsername());
        if (existUser > 0) {
            throw new ErrorException("Email ou Username já existe");
        }

        if(!user.getEmail().equals("") && user.getEmail() != null){
            myUser.setEmail(user.getEmail());
        }

        if(!user.getUsername().equals("") && user.getUsername() != null){
            myUser.setUsername(user.getUsername());
        }

        if(!user.getSenha().equals("") && user.getSenha() != null){
            myUser.setSenha(bCrypt.encode(user.getSenha()));
        }

        userRepository.save(myUser);

        return null;
    }

    public Void deleteMyData(String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);

        userRepository.deleteById(myUser.getId());
        return null;
    }

    public List<UserModel> getAllCliente(String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        return userRepository.findByRole(0);
    }

    public List<UserModel> getAllFuncionario(String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        return userRepository.findByRole(1);
    }

    public UserModel getClienteById(Integer id, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        Optional<UserModel> optional = userRepository.findClienteById(id);
        if (optional.isEmpty()) {
            throw new ErrorException("Cliente não existe!");
        }
        return optional.get();
    }

    public UserModel getFuncionarioById(Integer id, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        Optional<UserModel> optional = userRepository.findFuncionarioById(id);
        if (optional.isEmpty()) {
            throw new ErrorException("Funcionario não existe!");
        }
        return optional.get();
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
            throw new ErrorException("Email ou Username já existe");
        }

        if (String.valueOf(user.getCpf()).length() != 11) {
            throw new ErrorException("CPF invalido");
        }

        Integer existCPF = clienteRepository.existCPF(user.getCpf());
        if (existCPF > 0) {
            throw new ErrorException("CPF já existe");
        }

        UserModel userModel = new UserModel(user);
        userModel.setSenha(bCrypt.encode(userModel.getSenha()));

        ClienteModel clienteModel = new ClienteModel(user, userModel);

        EnderecoModel enderecoModel = new EnderecoModel(user, clienteModel);

        userRepository.save(userModel);
        clienteRepository.save(clienteModel);
        enderecoRepository.save(enderecoModel);

        return null;
    }

    public Void createFuncionario(FuncionarioDTOModel user, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        try {
            if(user.isNull()){
                throw new ErrorException("Parametros invalido");
            }
        } catch (Exception e) {
            throw new ErrorException("Parametros invalido");
        }

        Integer existUser = userRepository.existUser(user.getEmail(), user.getUsername());
        if (existUser > 0) {
            throw new ErrorException("Email ou Username já existe");
        }

        if (String.valueOf(user.getCpf()).length() != 11) {
            throw new ErrorException("CPF invalido");
        }

        Integer existCPF = funcionarioRepository.existCPF(user.getCpf());
        if (existCPF > 0) {
            throw new ErrorException("CPF já existe");
        }

        UserModel userModel = new UserModel(user);
        userModel.setSenha(bCrypt.encode(userModel.getSenha()));

        FuncionarioModel funcionarioModel = new FuncionarioModel(user, userModel);

        userRepository.save(userModel);
        funcionarioRepository.save(funcionarioModel);

        return null;
    }

    public Void updateFuncionario(UserModel user, Integer id, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        Optional<UserModel> optional = userRepository.findFuncionarioById(id);
        if (optional.isEmpty()) {
            throw new ErrorException("Funcionario não existe!");
        }

        Integer existUser = userRepository.existUser(user.getEmail(), user.getUsername());
        if (existUser > 0) {
            throw new ErrorException("Email ou Username já existe");
        }

        UserModel userToUpdate = optional.get();

        if(!user.getEmail().equals("") && user.getEmail() != null){
            userToUpdate.setEmail(user.getEmail());
        }

        if(!user.getUsername().equals("") && user.getUsername() != null){
            userToUpdate.setUsername(user.getUsername());
        }

        if(!user.getSenha().equals("") && user.getSenha() != null){
            userToUpdate.setSenha(bCrypt.encode(user.getSenha()));
        }

        userRepository.save(userToUpdate);
        return null;
    }

    public Void deleteCliente(Integer id, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        Optional<UserModel> optional = userRepository.findClienteById(id);
        if (optional.isEmpty()) {
            throw new ErrorException("Cliente não existe!");
        }
        userRepository.deleteById(id);
        return null;
    }

    public Void deleteFuncionario(Integer id, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyFuncionarios(myUser);

        Optional<UserModel> optional = userRepository.findFuncionarioById(id);
        if (optional.isEmpty()) {
            throw new ErrorException("Funcionario não existe!");
        }
        userRepository.deleteById(id);
        return null;
    }

    public UserModel getUser(String username){
        Optional<UserModel> optional = userRepository.findByUsername(username);
        if (optional.isEmpty()) {
            return null;
        }
        return optional.get();
    }
}
