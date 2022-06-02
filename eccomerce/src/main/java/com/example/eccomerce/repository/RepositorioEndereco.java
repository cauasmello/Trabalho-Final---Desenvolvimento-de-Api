package com.example.eccomerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eccomerce.model.Endereco;

public interface RepositorioEndereco extends JpaRepository <Endereco, Integer> {

}
