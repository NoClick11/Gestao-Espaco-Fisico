package com.manuelneto.gestaoespacofisico.mapper;

import com.manuelneto.gestaoespacofisico.dto.EspacoFisicoDTO;
import com.manuelneto.gestaoespacofisico.entity.EspacoFisico;
import org.springframework.stereotype.Component;

@Component
public class EspacoFisicoMapper {

    public EspacoFisicoDTO toDTO(EspacoFisico espaco) {
        if (espaco == null) return null;
        EspacoFisicoDTO dto = new EspacoFisicoDTO();
        dto.setId(espaco.getId());
        dto.setNome(espaco.getNome());
        dto.setMetragem(espaco.getMetragem());
        return dto;
    }

    public EspacoFisico toEntity(EspacoFisicoDTO dto) {
        if (dto == null) return null;
        EspacoFisico espaco = new EspacoFisico();
        espaco.setId(dto.getId());
        espaco.setNome(dto.getNome());
        espaco.setMetragem(dto.getMetragem());
        return espaco;
    }
}

