package com.manuelneto.gestaoespacofisico.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class EspacoFisicoDTO {
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O nome é obrigatório")
    private String localizacao;

    @NotBlank(message = "O nome é obrigatório")
    private BigDecimal metragem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public BigDecimal getMetragem() {
        return metragem;
    }

    public void setMetragem(BigDecimal metragem) {
        this.metragem = metragem;
    }
}
