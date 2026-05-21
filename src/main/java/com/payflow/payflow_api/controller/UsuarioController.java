package com.payflow.payflow_api.controller;

import com.payflow.payflow_api.dto.request.CriarUsuarioRequest;
import com.payflow.payflow_api.dto.response.UsuarioResponse;
import com.payflow.payflow_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public UsuarioResponse buscarUsuario(@Validated @PathVariable Long id) {
        return usuarioService.buscarUsuario(id);
    }
    @GetMapping()
    @ResponseStatus(OK)
    public List<UsuarioResponse> buscarTodos() {
        return usuarioService.buscarTodos();
    }
}
