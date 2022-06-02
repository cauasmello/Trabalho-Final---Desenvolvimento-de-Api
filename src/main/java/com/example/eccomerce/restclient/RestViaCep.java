package com.example.eccomerce.restclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.eccomerce.model.ViaCepDTOModel;

@Component
public class RestViaCep {
    public ViaCepDTOModel getViaCep(String cep){
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        RestTemplate rest = new RestTemplate();
        ViaCepDTOModel enderecoReturn = rest.getForObject(url, ViaCepDTOModel.class);
        return enderecoReturn;

    }
}
