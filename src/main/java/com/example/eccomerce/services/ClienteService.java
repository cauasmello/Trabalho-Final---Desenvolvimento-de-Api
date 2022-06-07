package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.ClienteDTOModel;
import com.example.eccomerce.models.ClienteModel;
import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.repositories.ClienteRepository;
import com.example.eccomerce.resources.ToolsResource;
import com.example.eccomerce.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository repositorio;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ToolsResource tools;

    public ClienteModel get(String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyClientes(myUser);

        return myUser.getCliente();
    }

    public Void put(ClienteDTOModel clienteNew, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyClientes(myUser);

        ClienteModel clienteModel = myUser.getCliente();
        tools.existCliente(clienteModel);

        if (clienteNew.getNome() != null && !clienteNew.getNome().equals("")) {
            clienteModel.setNome(clienteNew.getNome());
        }

        if (clienteNew.getTelefone() != null) {
            clienteModel.setTelefone(clienteNew.getTelefone());
        }

        if (clienteNew.getNascimento() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            clienteModel.setNascimento(LocalDate.parse(clienteNew.getNascimento(), formatter));
        }

        repositorio.save(clienteModel);
        return null;
    }
}
	          
