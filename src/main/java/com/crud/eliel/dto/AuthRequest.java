package com.crud.eliel.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String senha;
}