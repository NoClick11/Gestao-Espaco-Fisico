package com.manuelneto.gestaoespacofisico.mapper;

import com.manuelneto.gestaoespacofisico.dto.SolicitacaoDTO;
import com.manuelneto.gestaoespacofisico.entity.Equipamento;
import com.manuelneto.gestaoespacofisico.entity.Solicitacao;
import com.manuelneto.gestaoespacofisico.service.EquipamentoService;
import com.manuelneto.gestaoespacofisico.service.EspacoFisicoService;
import com.manuelneto.gestaoespacofisico.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Set;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SolicitacaoMapper {

    @Autowired
    private EspacoFisicoService espacoFisicoService;

    @Autowired
    private EquipamentoService equipamentoService;

    @Autowired
    private UsuarioService usuarioService;

    public SolicitacaoDTO toDTO(Solicitacao solicitacao) {
        if (solicitacao == null) return null;

        SolicitacaoDTO dto = new SolicitacaoDTO();
        dto.setId(solicitacao.getId());
        dto.setEspacoFisicoId(solicitacao.getEspacoFisico() != null ? solicitacao.getEspacoFisico().getId() : null);
        dto.setSolicitanteId(solicitacao.getSolicitante() != null ? solicitacao.getSolicitante().getId() : null);
        dto.setDataReserva(solicitacao.getDataReserva());
        dto.setHoraInicio(solicitacao.getHoraInicio());
        dto.setHoraFim(solicitacao.getHoraFim());
        dto.setStatus(solicitacao.getStatus());

        if (solicitacao.getEquipamentos() != null) {
            Set<Long> equipamentosIds = solicitacao.getEquipamentos().stream()
                    .map(Equipamento::getId)
                    .collect(Collectors.toSet());
            dto.setEquipamentosIds(equipamentosIds);
        }

        return dto;
    }

    public Solicitacao toEntity(SolicitacaoDTO dto) {
        if (dto == null) return null;
        System.out.println("DataReserva recebida no DTO: " + dto.getDataReserva());

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(dto.getId());
        solicitacao.setEspacoFisico(espacoFisicoService.buscarPorId(dto.getEspacoFisicoId()));
        solicitacao.setSolicitante(usuarioService.buscarPorId(dto.getSolicitanteId()));
        solicitacao.setDataReserva(dto.getDataReserva());
        solicitacao.setHoraInicio(dto.getHoraInicio());
        solicitacao.setHoraFim(dto.getHoraFim());
        solicitacao.setStatus(dto.getStatus() != null ? dto.getStatus() : "PENDENTE");

        if (dto.getEquipamentosIds() != null) {
            Set<Equipamento> equipamentos = dto.getEquipamentosIds().stream()
                    .map(id -> equipamentoService.buscarPorId(id))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            solicitacao.setEquipamentos(equipamentos);
        }

        return solicitacao;
    }
}