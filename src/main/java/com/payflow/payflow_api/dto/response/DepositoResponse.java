package com.payflow.payflow_api.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record DepositoResponse(UUID numeroDaConta, BigDecimal valor) {
}
