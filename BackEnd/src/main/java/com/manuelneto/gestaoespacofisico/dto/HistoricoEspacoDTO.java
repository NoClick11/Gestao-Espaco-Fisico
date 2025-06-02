package com.manuelneto.gestaoespacofisico.dto;

import java.util.List;

public class HistoricoEspacoDTO {
    private Long espacoId;
    private String espacoNome;
    private List<SolicitacaoDTO> solicitacoes;

    public Long getEspacoId() {
        return espacoId;
    }

    public void setEspacoId(Long espacoId) {
        this.espacoId = espacoId;
    }

    public String getEspacoNome() {
        return espacoNome;
    }

    public void setEspacoNome(String espacoNome) {
        this.espacoNome = espacoNome;
    }

    public List<SolicitacaoDTO> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(List<SolicitacaoDTO> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }
}