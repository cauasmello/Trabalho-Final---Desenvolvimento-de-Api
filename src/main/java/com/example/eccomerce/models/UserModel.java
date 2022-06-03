package com.example.eccomerce.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String senha;

    private Integer role = 1;

    @OneToMany(mappedBy="user")
    private Set<ClienteModel> cliente;

    public UserModel() {
        super();
    }

    public UserModel(Integer id, String email, String username, String senha) {
        super();
        this.id = id;
        this.email = email;
        this.username = username;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getRole() {
        return role;
    }
}
