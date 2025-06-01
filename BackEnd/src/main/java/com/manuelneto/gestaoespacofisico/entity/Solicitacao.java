package com.manuelneto.gestaoespacofisico.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "solicitacoes")
public class Solicitacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_solicitacao")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_espaco", nullable = false)
	private EspacoFisico espacoFisico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_solicitante", nullable = false)
	private Usuario solicitante;

	@Column(name = "data_reserva", nullable = false)
	private LocalDate dataReserva;

	@Column(name = "hora_inicio", nullable = false)
	private LocalTime horaInicio;

	@Column(name = "hora_fim", nullable = false)
	private LocalTime horaFim;

	@Column(nullable = false)
	private String status;

	@Column(columnDefinition = "TEXT")
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "solicitacao_equipamentos",
			joinColumns = @JoinColumn(name = "id_solicitacao"),
			inverseJoinColumns = @JoinColumn(name = "id_equipamento")
	)
	private Set<Equipamento> equipamentos;

	@Column(name = "data_criacao", nullable = false, updatable = false)
	private LocalDate dataCriacao = LocalDate.now();

	// Construtores
	public Solicitacao() {
		this.status = "PENDENTE"; // Valor padrão
	}

	// Getters e Setters
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(Set<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}