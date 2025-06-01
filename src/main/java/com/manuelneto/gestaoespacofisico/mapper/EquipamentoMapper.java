package com.manuelneto.gestaoespacofisico.mapper;

import com.manuelneto.gestaoespacofisico.dto.EquipamentoDTO;
import com.manuelneto.gestaoespacofisico.entity.Equipamento;
import org.springframework.stereotype.Component;

@Component
public class EquipamentoMapper {

    public EquipamentoDTO toDTO(Equipamento equipamento) {
        if (equipamento == null) return null;
        EquipamentoDTO dto = new EquipamentoDTO();
        dto.setId(equipamento.getId());  // CORREÇÃO: chamada correta sem parâmetro
        dto.setNome(equipamento.getNome());
        return dto;
    }

    public Equipamento toEntity(EquipamentoDTO dto) {
        if (dto == null) return null;
        Equipamento equipamento = new Equipamento();
        equipamento.setId(dto.getId());
        equipamento.setNome(dto.getNome());  // CORREÇÃO: estava chamando getId com parâmetro
        return equipamento;
    }
}
