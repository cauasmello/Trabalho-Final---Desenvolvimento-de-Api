package com.example.eccomerce.models;

import javax.persistence.*;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String username;

    private String senha;

    private Integer role;

    public UserModel() {
        super();
    }

    public UserModel(Integer id, String email, String username, String senha, Integer role) {
        super();
        this.id = id;
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.role = role;
    }

}
