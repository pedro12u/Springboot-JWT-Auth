package com.crud.eliel.controller;

import com.crud.eliel.dto.*;
import com.crud.eliel.repository.UserRepository;
import com.crud.eliel.security.JwtUtil;
import com.crud.eliel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        var user = repo.findByEmail(request.getEmail()).orElseThrow();
        if (encoder.matches(request.getSenha(), user.getSenha())) {
            return new AuthResponse(jwtUtil.generateToken(user.getEmail()));
        }
        throw new RuntimeException("Informações inválidas");
    }
}
