package com.example.eccomerce.repositories;

import com.example.eccomerce.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
