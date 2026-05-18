package com.payflow.payflow_api.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record RealizarTransferenciaRequest(UUID contaOrigem, UUID contaDestino, BigDecimal valor) {
}
