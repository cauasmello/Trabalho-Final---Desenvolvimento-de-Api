package com.example.eccomerce.config;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.UserModel;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolsConfig {

	public void onlyFuncionarios(UserModel user) throws ErrorException {
		if(user.getRole() != 1){
			throw new ErrorException("Permição negada!");
		}
	}

	public void existUser(UserModel user) throws ErrorException {
		if (user == null) {
			throw new ErrorException("Cliente não existe!");
		}
	}

}
