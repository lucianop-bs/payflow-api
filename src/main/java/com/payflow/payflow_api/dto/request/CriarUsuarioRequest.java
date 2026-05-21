package com.payflow.payflow_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarUsuarioRequest(
        @NotBlank(message = "Não aceitar campo em branco") String nome,
        @Email @NotBlank String email,
        @Size(min = 3) @NotBlank String senha,
        @Size(min = 11) @NotBlank String cpf
) {
}
