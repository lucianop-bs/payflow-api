package com.payflow.payflow_api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String erro,
        List<String> mensagens,
        LocalDateTime timestamp
) {}
