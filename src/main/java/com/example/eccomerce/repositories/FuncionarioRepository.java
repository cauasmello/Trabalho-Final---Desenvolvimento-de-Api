package com.example.eccomerce.repositories;

import com.example.eccomerce.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Integer> {
}
