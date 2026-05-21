package com.payflow.payflow_api.dto.mapper;

import com.payflow.payflow_api.domain.Usuario;
import com.payflow.payflow_api.dto.request.CriarUsuarioRequest;
import com.payflow.payflow_api.dto.response.UsuarioResponse;
import com.payflow.payflow_api.utils.CpfUtils;

public class UsuarioMapper {
    public static Usuario toEntity(CriarUsuarioRequest request) {

        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setCpf(CpfUtils.limpar(request.cpf()));
        return usuario;
    }
    public static UsuarioResponse mapToResponse( Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                CpfUtils.formatar(usuario.getCpf()));
    }
}
