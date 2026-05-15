package com.example.LibraryManagement.repository;

import com.example.LibraryManagement.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

}
