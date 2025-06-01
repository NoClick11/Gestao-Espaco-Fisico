package com.manuelneto.gestaoespacofisico.service;

import java.util.List;

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
		return solicitacaoRepository.save(solicitacao);
	}
	
	public Solicitacao buscarPorID(Long Id) {
		return solicitacaoRepository.findById(Id).orElse(null);
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
