package com.example.LibraryManagement.controller;

import com.example.LibraryManagement.dto.LoginRequest;
import com.example.LibraryManagement.entity.Users;
import com.example.LibraryManagement.repository.UserRepository;
import com.example.LibraryManagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return repository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        Users user=
                repository.findByUsername((
                        request.getUsername()
                        ));
        if(user != null &&
        user.getPassword().equals(
                request.getPassword()
        )){
            return jwtUtil.generateToken(
                    user.getUsername()
            );
        }

        return "Invalid Username or Password";
    }
}
