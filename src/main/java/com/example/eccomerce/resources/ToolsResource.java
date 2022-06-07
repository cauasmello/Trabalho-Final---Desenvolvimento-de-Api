package com.example.eccomerce.resources;

import org.springframework.context.annotation.Configuration;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.ClienteModel;
import com.example.eccomerce.models.FuncionarioModel;
import com.example.eccomerce.models.UserModel;

@Configuration
public class ToolsResource {

	public void onlyFuncionarios(UserModel user) throws ErrorException {
		if(user.getRole() != 1){
			throw new ErrorException("Permição negada!");
		}
	}

	public void onlyClientes(UserModel user) throws ErrorException {
		if(user.getRole() != 0){
			throw new ErrorException("Permição negada!");
		}
	}

	public void existUser(UserModel user) throws ErrorException {
		if (user == null) {
			throw new ErrorException("Usuario não existe!");
		}
	}

	public void existCliente(ClienteModel cliente) throws ErrorException {
		if (cliente == null) {
			throw new ErrorException("Cliente não existe!");
		}
	}
		
	public void existFuncionario(FuncionarioModel funcionario) throws ErrorException {
		if (funcionario == null) {
			throw new ErrorException("Funcionário não existe!");
			}
	}

}
