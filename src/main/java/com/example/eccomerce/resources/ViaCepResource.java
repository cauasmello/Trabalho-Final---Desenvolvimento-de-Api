package com.example.eccomerce.resources;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.eccomerce.models.ViaCepModel;

@Component
public class ViaCepResource {

    public ViaCepModel getViaCep(String cep){
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        RestTemplate rest = new RestTemplate();
        return rest.getForObject(url, ViaCepModel.class);

    }
}
