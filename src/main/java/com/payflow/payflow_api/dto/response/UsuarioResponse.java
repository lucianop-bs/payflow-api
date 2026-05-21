package com.payflow.payflow_api.dto.response;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String cpf
) {}