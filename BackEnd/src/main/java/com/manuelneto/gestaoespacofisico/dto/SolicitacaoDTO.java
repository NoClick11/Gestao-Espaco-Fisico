package com.manuelneto.gestaoespacofisico.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class SolicitacaoDTO {
    private Long id;
    private Long espacoFisicoId;
    private String espacoFisicoNome;
    private Long solicitanteId;
    private String solicitanteNome;
    private LocalDate dataReserva;
    private LocalDate dataSolicitacao;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String status;
    private Set<Long> equipamentosIds;
    private List<String> equipamentosNomes; // Alterado para List<String>

    public String getEspacoFisicoNome() {
        return espacoFisicoNome;
    }

    public void setEspacoFisicoNome(String espacoFisicoNome) {
        this.espacoFisicoNome = espacoFisicoNome;
    }

    public String getSolicitanteNome() {
        return solicitanteNome;
    }

    public void setSolicitanteNome(String solicitanteNome) {
        this.solicitanteNome = solicitanteNome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEspacoFisicoId() {
        return espacoFisicoId;
    }

    public void setEspacoFisicoId(Long espacoFisicoId) {
        this.espacoFisicoId = espacoFisicoId;
    }

    public Long getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(Long solicitanteId) {
        this.solicitanteId = solicitanteId;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Long> getEquipamentosIds() {
        return equipamentosIds;
    }

    public void setEquipamentosIds(Set<Long> equipamentosIds) {
        this.equipamentosIds = equipamentosIds;
    }

    public List<String> getEquipamentosNomes() {
        return equipamentosNomes;
    }

    public void setEquipamentosNomes(List<String> equipamentosNomes) {
        this.equipamentosNomes = equipamentosNomes;
    }
}