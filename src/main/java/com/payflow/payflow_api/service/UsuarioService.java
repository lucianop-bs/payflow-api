package com.payflow.payflow_api.service;

import com.payflow.payflow_api.domain.Usuario;
import com.payflow.payflow_api.dto.request.CriarUsuarioRequest;
import com.payflow.payflow_api.dto.response.UsuarioResponse;
import com.payflow.payflow_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse criarUsuario(CriarUsuarioRequest request) {

        Usuario usuario = new Usuario();
        usuario.setCpf(request.cpf());
        usuario.setEmail(request.email());
        usuario.setNome(request.nome());
        usuario.setSenha(request.senha());

        usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
