package com.crud.eliel.service;

import com.crud.eliel.dto.AuthRequest;
import com.crud.eliel.dto.AuthResponse;
import com.crud.eliel.dto.RegisterRequest;
import com.crud.eliel.entity.User;
import com.crud.eliel.repository.UserRepository;
import com.crud.eliel.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request) {
        var user = User.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(encoder.encode(request.getSenha()))
                .role(request.getRole())
                .build();
        repo.save(user);
        return "Usuário cadastrado com sucesso";
    }

    public AuthResponse login(AuthRequest request) {
        var user = repo.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Email não encontrado"));
        if (!encoder.matches(request.getSenha(), user.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}