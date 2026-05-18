package com.payflow.payflow_api.controller;

import com.payflow.payflow_api.dto.request.RealizarTransferenciaRequest;
import com.payflow.payflow_api.dto.response.TransacaoResponse;
import com.payflow.payflow_api.service.TransacaoService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public TransacaoResponse criarTransacao(@RequestBody RealizarTransferenciaRequest request) {
        return transacaoService.realizarTransferencia(request);
    }
}
