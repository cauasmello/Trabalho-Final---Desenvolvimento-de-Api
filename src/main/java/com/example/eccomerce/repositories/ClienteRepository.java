package com.example.eccomerce.repositories;

import com.example.eccomerce.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {
}
