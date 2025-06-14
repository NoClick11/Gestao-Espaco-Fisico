package com.manuelneto.gestaoespacofisico.mapper;

import com.manuelneto.gestaoespacofisico.dto.SolicitacaoDTO;
import com.manuelneto.gestaoespacofisico.entity.Equipamento;
import com.manuelneto.gestaoespacofisico.entity.Solicitacao;
import com.manuelneto.gestaoespacofisico.service.EquipamentoService;
import com.manuelneto.gestaoespacofisico.service.EspacoFisicoService;
import com.manuelneto.gestaoespacofisico.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
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

        // Espaço Físico
        dto.setEspacoFisicoId(solicitacao.getEspacoFisico() != null ? solicitacao.getEspacoFisico().getId() : null);
        dto.setEspacoFisicoNome(solicitacao.getEspacoFisico() != null ? solicitacao.getEspacoFisico().getNome() : null);

        // Solicitante
        dto.setSolicitanteId(solicitacao.getSolicitante() != null ? solicitacao.getSolicitante().getId() : null);
        dto.setSolicitanteNome(solicitacao.getSolicitante() != null ? solicitacao.getSolicitante().getNome() : null);

        // Datas e horários
        dto.setDataReserva(solicitacao.getDataReserva());
        dto.setHoraInicio(solicitacao.getHoraInicio());
        dto.setHoraFim(solicitacao.getHoraFim());
        dto.setStatus(solicitacao.getStatus());

        // Equipamentos
        if (solicitacao.getEquipamentos() != null) {
            Set<Long> equipamentosIds = solicitacao.getEquipamentos().stream()
                    .map(Equipamento::getId)
                    .collect(Collectors.toSet());
            dto.setEquipamentosIds(equipamentosIds);

            List<String> equipamentosNomes = solicitacao.getEquipamentos().stream()
                    .map(Equipamento::getNome)
                    .collect(Collectors.toList());
            dto.setEquipamentosNomes(equipamentosNomes);
        }

        return dto;
    }

    public Solicitacao toEntity(SolicitacaoDTO dto) {
        if (dto == null) return null;

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