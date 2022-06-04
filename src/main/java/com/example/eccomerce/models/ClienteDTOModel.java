package com.example.eccomerce.models;

import com.example.eccomerce.resources.ViaCepResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.time.LocalDate;

public class ClienteDTOModel {

    private String email;
    private String username;
    private String senha;
    private Integer role;

    private String nome;
    private Long cpf;
    private Long telefone;
    private LocalDate nascimento;

    private String cep;
    private String numero;
    private String complemento;

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

    @Autowired
    ViaCepResource viaCep;

    public ClienteDTOModel() {
        super();
    }

    public ClienteDTOModel(@NotNull String email, @NotNull String username, @NotNull String senha, @NotNull String nome, @NotNull Long cpf, @NotNull Long telefone, @NotNull LocalDate nascimento, @NotNull String cep, @NotNull String numero, @NotNull String complemento) {
        super();
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.role = 0;

        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.nascimento = nascimento;

        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;

        assert false;
        ViaCepModel viaCepModel = this.viaCep.getViaCep(cep);
        this.setViaCep(viaCepModel.getLogradouro(), viaCepModel.getBairro(), viaCepModel.getLocalidade(), viaCepModel.getUf());
    }

    public void setViaCep(@NotNull String rua, @NotNull String bairro, @NotNull String cidade, @NotNull String estado){
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public Integer getRole() {
        return role;
    }

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public Long getTelefone() {
        return telefone;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getEstado() {
        return estado;
    }

    public Boolean isNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            if(f.get(this) == null) return true;
        }
        return false;
    }
}
