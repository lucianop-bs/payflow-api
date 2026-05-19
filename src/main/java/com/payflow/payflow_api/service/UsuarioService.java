package com.payflow.payflow_api.service;

import com.payflow.payflow_api.domain.Usuario;
import com.payflow.payflow_api.dto.mapper.UsuarioResponseMapper;
import com.payflow.payflow_api.dto.request.CriarUsuarioRequest;
import com.payflow.payflow_api.dto.response.UsuarioResponse;
import com.payflow.payflow_api.exception.RecursoNaoEncontrado;
import com.payflow.payflow_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;



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
        return UsuarioResponseMapper.mapToResponse(usuario);
    }

    public UsuarioResponse buscarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuário não encontrado"));
        return UsuarioResponseMapper.mapToResponse(usuario);
    }

    public List<UsuarioResponse> buscarTodos() {
        List<Usuario> usuario = usuarioRepository.findAll();
        return usuario.stream().map(UsuarioResponseMapper::mapToResponse)
                .toList();
    }
}
