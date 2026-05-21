package com.payflow.payflow_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record RealizarTransferenciaRequest(@NotNull UUID contaOrigem, @NotNull UUID contaDestino,@NotNull @Positive BigDecimal valor) {
}
