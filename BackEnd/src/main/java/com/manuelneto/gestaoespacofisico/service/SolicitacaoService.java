package com.manuelneto.gestaoespacofisico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuelneto.gestaoespacofisico.entity.Solicitacao;
import com.manuelneto.gestaoespacofisico.repository.SolicitacaoRepository;

@Service
public class SolicitacaoService {
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	public List<Solicitacao> listarTodas() {
		return solicitacaoRepository.findAll();
	}
	
	public Solicitacao salvar (Solicitacao solicitacao) {
		System.out.println("DataReserva antes de salvar: " + solicitacao.getDataReserva());
		return solicitacaoRepository.save(solicitacao);
	}

	public Optional<Solicitacao> buscarPorID(Long id) {
		return solicitacaoRepository.findById(id);
	}

	public List<Solicitacao> buscarPorUsuarioId(Long usuarioId) {
		return solicitacaoRepository.findBySolicitanteId(usuarioId);
	}
	
	public List<Solicitacao> buscarPorStatus(String status) {
		return solicitacaoRepository.findByStatus(status);
	}
	
	public Long contarPendentes() {
		return solicitacaoRepository.countPendentes();
	}
	
	public void deletar(Long id) {
		solicitacaoRepository.deleteById(id);
	}
	
	
}
