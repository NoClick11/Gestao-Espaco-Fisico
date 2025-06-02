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

	public Solicitacao salvar(Solicitacao solicitacao) {
		List<Solicitacao> conflitos = solicitacaoRepository.findConflitosDeHorario(
				solicitacao.getEspacoFisico().getId(),
				solicitacao.getDataReserva(),
				solicitacao.getHoraInicio(),
				solicitacao.getHoraFim()
		);

		boolean temConflito = conflitos.stream().anyMatch(s -> !s.getId().equals(solicitacao.getId()));

		if (temConflito) {
			throw new RuntimeException("Já existe uma solicitação para esse espaço com horário conflitante.");
		}

		return solicitacaoRepository.save(solicitacao);
	}

	public Optional<Solicitacao> buscarPorID(Long id) {
		return solicitacaoRepository.findById(id);
	}

	public List<Solicitacao> buscarPorUsuarioId(Long usuarioId) {
		return solicitacaoRepository.findBySolicitanteId(usuarioId);
	}

	public List<Solicitacao> buscarPorEspacoId(Long espacoId) {
		return solicitacaoRepository.findByEspacoFisicoId(espacoId);
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
