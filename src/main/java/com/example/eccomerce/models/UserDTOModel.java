package com.example.eccomerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTOModel {

    private Integer id;

    private String email;

    private String username;

    @JsonIgnore
    private String senha;

    private String role;

    public UserDTOModel() {
        super();
    }

    public UserDTOModel(UserModel user) {
        super();
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.senha = user.getSenha();
        this.role = (user.getRole() == 1) ? "Funcionario" : "Cliente";

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
