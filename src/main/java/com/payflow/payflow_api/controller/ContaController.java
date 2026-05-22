package com.payflow.payflow_api.controller;

import com.payflow.payflow_api.dto.request.CriarContaRequest;
import com.payflow.payflow_api.dto.request.DepositarRequest;
import com.payflow.payflow_api.dto.response.ContaResponse;
import com.payflow.payflow_api.dto.response.DepositoResponse;
import com.payflow.payflow_api.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/contas")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ContaResponse criarConta(@Valid @RequestBody CriarContaRequest request) {
        return contaService.criarConta(request);
    }

    @PatchMapping("/depositar")
    @ResponseStatus(OK)
    public DepositoResponse depositar(@Valid @RequestBody DepositarRequest request) {
        return contaService.depositar(request);
    }
    @GetMapping("/extrato/{numeroConta}")
    @ResponseStatus(OK)
    public String extratoConta(@Validated @PathVariable UUID numeroConta) {
        return contaService.extratoConta(numeroConta);
    }

    @PostMapping("/extrato/{numeroConta}/exportar")
    @ResponseStatus(NO_CONTENT)
    public void exportarExtrato(@Validated @PathVariable UUID numeroConta) {
       contaService.exportarExtrato(numeroConta);
    }
}
