package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.GeneralException;
import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<UserModel> getAll() {
        List<UserModel> list = repository.findAll();
        System.out.println(list);
        return list;
    }

    public String create(UserModel user) throws GeneralException {
        if(user.getEmail() == null){
            throw new GeneralException("Email Invalido");
        }

        if(user.getUsername() == null){
            throw new GeneralException("Username Invalido");
        }

        if(user.getSenha() == null){
            throw new GeneralException("Senha Invalido");
        }
        repository.save(user);
        return "Criado com sucesso!";
    }
}
