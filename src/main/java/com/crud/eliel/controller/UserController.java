package com.crud.eliel.controller;

import com.crud.eliel.entity.User;
import com.crud.eliel.repository.UserRepository;
import com.crud.eliel.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repo;

    @GetMapping("/admin/users")
    public List<User> listarUsuarios() {
        return repo.findAll();
    }

    @GetMapping("/user/me")
    public User meuPerfil(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userDetails.getUser();
    }

    @PutMapping("/user/me")
    public User atualizarPerfil(@AuthenticationPrincipal CustomUserDetails userDetails,
                                @RequestBody User novo) {
        var user = userDetails.getUser();
        user.setNome(novo.getNome());
        repo.save(user);
        return user;
    }

    @DeleteMapping("/admin/user/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        repo.deleteById(id);
        return "Usuário deletado";
    }
}