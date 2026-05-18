package com.payflow.payflow_api.controller;

import com.payflow.payflow_api.dto.request.CriarContaRequest;
import com.payflow.payflow_api.dto.request.DepositarRequest;
import com.payflow.payflow_api.dto.response.ContaResponse;
import com.payflow.payflow_api.dto.response.DepositoResponse;
import com.payflow.payflow_api.service.ContaService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/contas")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ContaResponse criarConta(@RequestBody CriarContaRequest request) {
        return contaService.criarConta(request);
    }

    @PatchMapping("/depositar")
    @ResponseStatus(OK)
    public DepositoResponse depositar(@RequestBody DepositarRequest request) {
        return contaService.depositar(request);
    }

}
