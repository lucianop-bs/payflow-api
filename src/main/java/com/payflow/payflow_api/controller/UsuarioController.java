package com.payflow.payflow_api.controller;

import com.payflow.payflow_api.dto.request.CriarUsuarioRequest;
import com.payflow.payflow_api.dto.response.UsuarioResponse;
import com.payflow.payflow_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UsuarioResponse criarUsuario(@RequestBody @Valid CriarUsuarioRequest request) {
        return usuarioService.criarUsuario(request);
    }
}
