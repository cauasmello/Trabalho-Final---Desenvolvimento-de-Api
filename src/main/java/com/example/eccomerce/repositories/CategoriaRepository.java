package com.example.eccomerce.repositories;

import com.example.eccomerce.models.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Integer> {
}
