package com.example.eccomerce.repositories;

import com.example.eccomerce.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {
}
