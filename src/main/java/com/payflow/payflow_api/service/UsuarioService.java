package com.payflow.payflow_api.service;

import com.payflow.payflow_api.domain.Usuario;
import com.payflow.payflow_api.dto.mapper.UsuarioMapper;
import com.payflow.payflow_api.dto.request.CriarUsuarioRequest;
import com.payflow.payflow_api.dto.response.UsuarioResponse;
import com.payflow.payflow_api.exception.RecursoNaoEncontrado;
import com.payflow.payflow_api.exception.RegraDeNegocioException;
import com.payflow.payflow_api.repository.UsuarioRepository;
import com.payflow.payflow_api.utils.CpfUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse criarUsuario(CriarUsuarioRequest request) {

        var usuario = UsuarioMapper.toEntity(request);
        if (!CpfUtils.validaCPF(usuario.getCpf())) {
            throw new RegraDeNegocioException("CPF invalido");
        }
        
        usuarioRepository.save(usuario);
        return UsuarioMapper.mapToResponse(usuario);
    }

    public UsuarioResponse buscarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuário não encontrado"));
        return UsuarioMapper.mapToResponse(usuario);
    }

    public List<UsuarioResponse> buscarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioMapper::mapToResponse)
                .toList();
    }
}
