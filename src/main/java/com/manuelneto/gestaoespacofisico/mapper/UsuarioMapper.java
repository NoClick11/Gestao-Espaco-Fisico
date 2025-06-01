package com.manuelneto.gestaoespacofisico.mapper;

import com.manuelneto.gestaoespacofisico.dto.UsuarioDTO;
import com.manuelneto.gestaoespacofisico.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTipoUsuario(usuario.getTipoUsuario());
        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTipoUsuario(dto.getTipoUsuario());
        return usuario;
    }
}
