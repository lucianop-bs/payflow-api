package com.payflow.payflow_api.dto.mapper;

import com.payflow.payflow_api.domain.Usuario;
import com.payflow.payflow_api.dto.response.UsuarioResponse;

import java.util.List;

public class UsuarioResponseMapper {
    public static UsuarioResponse mapToResponse( Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
