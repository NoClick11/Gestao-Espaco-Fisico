package com.manuelneto.gestaoespacofisico.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitacoes")
public class Solicitacao {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_solicitacao")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_espaco")
	private EspacoFisico espacoFisico;
	
	@ManyToOne
	@JoinColumn(name = "id_solicitante")
	private Usuario solicitante;
	
	@Column(name = "data_reserva")
	private LocalDate dataReserva;
	
	@Column(name = "hora_inicio")
	private LocalTime horaInicio;
	
	@Column(name = "hora_fim")
	private LocalTime horaFim;
	
	private String status;
	private String equipamentos;
	
	public Solicitacao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EspacoFisico getEspacoFisico() {
		return espacoFisico;
	}

	public void setEspacoFisico(EspacoFisico espacoFisico) {
		this.espacoFisico = espacoFisico;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
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
