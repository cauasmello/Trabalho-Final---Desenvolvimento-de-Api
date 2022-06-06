package com.example.eccomerce.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.FuncionarioDTOModel;
import com.example.eccomerce.models.FuncionarioModel;
import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.repositories.FuncionarioRepository;
import com.example.eccomerce.resources.ToolsResource;
import com.example.eccomerce.security.JWTUtil;

@Service
public class FuncionarioService {

	    @Autowired
	    FuncionarioRepository repositorio;

	    @Autowired
	    JWTUtil jwtUtil;

	    @Autowired
	    ToolsResource tools;

	    public FuncionarioModel get(String token) throws ErrorException {
	        UserModel myUser = jwtUtil.getLoggedUser(token);
	        tools.existUser(myUser);
	        return myUser.getFuncionario();
	    }

	    public Void put(FuncionarioDTOModel funcionarioNew, String token) throws ErrorException {
	        UserModel myUser = jwtUtil.getLoggedUser(token);
	        tools.existUser(myUser);
	        FuncionarioModel funcionarioModel = myUser.getFuncionario();
	        tools.existFuncionario(funcionarioModel);

	        if (funcionarioNew.getNome() != null && !funcionarioNew.getNome().equals("")) {
	            funcionarioModel.setNome(funcionarioNew.getNome());
	        }

	        if (funcionarioNew.getTelefone() != null) {
	            funcionarioModel.setTelefone(funcionarioNew.getTelefone());
	        }

	        if (funcionarioNew.getNascimento() != null) {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	            funcionarioModel.setNascimento(LocalDate.parse(funcionarioNew.getNascimento(), formatter));
	        }

	        repositorio.save(funcionarioModel);
	        return null;
	    }
}
