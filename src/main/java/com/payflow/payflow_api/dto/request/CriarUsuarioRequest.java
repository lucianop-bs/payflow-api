package com.payflow.payflow_api.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CriarUsuarioRequest(
        @NotEmpty(message = "Não aceitar campo em branco") String nome,
        String email,
        String senha,
        String cpf
) {
}
