package com.manuelneto.gestaoespacofisico.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class SolicitacaoDTO {
    private Long id;
    private String descricao;
    private LocalDateTime dataSolicitacao;
    private String status;
    private Set<EquipamentoDTO> equipamentos;  // agora é um set de DTOs

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<EquipamentoDTO> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(Set<EquipamentoDTO> equipamentos) {
        this.equipamentos = equipamentos;
    }
}