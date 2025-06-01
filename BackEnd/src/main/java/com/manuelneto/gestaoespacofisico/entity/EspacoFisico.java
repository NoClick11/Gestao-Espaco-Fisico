package com.manuelneto.gestaoespacofisico.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name= "espacos_fisicos")
public class EspacoFisico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_espaco")
	private Long Id;
	
	private String nome;
	private String tipo;
	private BigDecimal metragem;
	
	@ManyToMany
	@JoinTable(
			name = "espaco_equipamentos",
			joinColumns = @JoinColumn(name = "id_espaco"),
			inverseJoinColumns = @JoinColumn(name = "id_equipamento")
			)
	private List<Equipamento> equipamentos;
	
	public EspacoFisico() {
	}

	public EspacoFisico(Long id, String nome, String tipo, BigDecimal metragem, List<Equipamento> equipamentos) {
		Id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.metragem = metragem;
		this.equipamentos = equipamentos;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getMetragem() {
		return metragem;
	}

	public void setMetragem(BigDecimal metragem) {
		this.metragem = metragem;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

}
