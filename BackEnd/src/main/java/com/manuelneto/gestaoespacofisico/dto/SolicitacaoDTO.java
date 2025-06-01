package com.manuelneto.gestaoespacofisico.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class SolicitacaoDTO {
    private Long id;

    @NotNull(message = "Espaço físico é obrigatório")
    private Long espacoFisicoId;

    @NotNull(message = "Solicitante é obrigatório")
    private Long solicitanteId;

    @NotNull(message = "Data da reserva é obrigatória")
    private LocalDate dataReserva;

    @NotNull(message = "Hora de início é obrigatória")
    private LocalTime horaInicio;

    @NotNull(message = "Hora de fim é obrigatória")
    private LocalTime horaFim;

    private String status;
    private String equipamentos;
    private Set<Long> equipamentosIds;

    // Getters e Setters
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
}