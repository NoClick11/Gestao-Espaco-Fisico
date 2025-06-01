package com.manuelneto.gestaoespacofisico.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class SolicitacaoDTO {
    private Long id;
    private EspacoFisicoDTO espacoFisico;
    private UsuarioDTO solicitante;
    private LocalDate dataReserva;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String status;
    private String equipamentos;

    public SolicitacaoDTO() {
    }

    public SolicitacaoDTO(Long id, EspacoFisicoDTO espacoFisico, UsuarioDTO solicitante, LocalDate dataReserva, LocalTime horaInicio, LocalTime horaFim, String status, String equipamentos) {
        this.id = id;
        this.espacoFisico = espacoFisico;
        this.solicitante = solicitante;
        this.dataReserva = dataReserva;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.status = status;
        this.equipamentos = equipamentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EspacoFisicoDTO getEspacoFisico() {
        return espacoFisico;
    }

    public void setEspacoFisico(EspacoFisicoDTO espacoFisico) {
        this.espacoFisico = espacoFisico;
    }

    public UsuarioDTO getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(UsuarioDTO solicitante) {
        this.solicitante = solicitante;
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

    public String getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(String equipamentos) {
        this.equipamentos = equipamentos;
    }
}