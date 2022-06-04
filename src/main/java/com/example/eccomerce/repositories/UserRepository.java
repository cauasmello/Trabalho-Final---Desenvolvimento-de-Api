package com.example.eccomerce.repositories;

import com.example.eccomerce.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    @Query("SELECT C FROM UserModel C where C.username=:username") List<UserModel>
    findByUsername (@Param("username") String username);

    @Query("SELECT C FROM UserModel C where C.email=:email") List<UserModel>
    findByEmail (@Param("email") String email);

    @Query("SELECT count(C) FROM UserModel C where C.email=:email or C.username=:username")
    Integer existUser (@Param("email") String email,@Param("username") String username);

    @Query("SELECT C FROM UserModel C where C.role=:role")
    List<UserModel> findByRole (@Param("role") Integer role);
}
