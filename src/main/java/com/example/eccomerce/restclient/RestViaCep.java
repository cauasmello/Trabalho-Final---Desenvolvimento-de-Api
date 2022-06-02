package com.example.eccomerce.restclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.eccomerce.model.ViaCepDTO;

@Component
public class RestViaCep {
    public ViaCepDTO getViaCep(String cep){
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        RestTemplate rest = new RestTemplate();
        ViaCepDTO enderecoReturn = rest.getForObject(url, ViaCepDTO.class);
        return enderecoReturn;

    }
}
