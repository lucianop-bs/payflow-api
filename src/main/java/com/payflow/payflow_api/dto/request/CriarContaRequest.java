package com.payflow.payflow_api.dto.request;

import jakarta.validation.constraints.NotNull;

public record CriarContaRequest(@NotNull Long idUsuario) {
}
