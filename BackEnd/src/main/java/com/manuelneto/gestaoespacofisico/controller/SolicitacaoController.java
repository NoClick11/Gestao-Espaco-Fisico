package com.manuelneto.gestaoespacofisico.controller;

import com.manuelneto.gestaoespacofisico.dto.AtualizarStatusDTO;
import com.manuelneto.gestaoespacofisico.dto.HistoricoEspacoDTO;
import com.manuelneto.gestaoespacofisico.dto.SolicitacaoDTO;
import com.manuelneto.gestaoespacofisico.entity.EspacoFisico;
import com.manuelneto.gestaoespacofisico.entity.Solicitacao;
import com.manuelneto.gestaoespacofisico.mapper.SolicitacaoMapper;
import com.manuelneto.gestaoespacofisico.service.EspacoFisicoService;
import com.manuelneto.gestaoespacofisico.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/solicitacoes")
@CrossOrigin(origins = "*")
public class SolicitacaoController {

	@Autowired
	private SolicitacaoService solicitacaoService;

	@Autowired
	private SolicitacaoMapper solicitacaoMapper;

	@Autowired
	private EspacoFisicoService espacoFisicoService;

	@GetMapping
	public List<SolicitacaoDTO> listarTodas() {
		return solicitacaoService.listarTodas()
				.stream()
				.map(solicitacaoMapper::toDTO)
				.collect(Collectors.toList());
	}

	@GetMapping("/historico-por-espaco/{espacoNome}")
	public ResponseEntity<HistoricoEspacoDTO> getHistoricoPorEspacoNome(
			@PathVariable String espacoNome) {

		EspacoFisico espaco = espacoFisicoService.buscarPorNome(espacoNome);
		if (espaco == null) {
			return ResponseEntity.notFound().build();
		}

		List<Solicitacao> solicitacoes = solicitacaoService.buscarPorEspacoId(espaco.getId());
		HistoricoEspacoDTO historico = new HistoricoEspacoDTO();
		historico.setEspacoId(espaco.getId());
		historico.setEspacoNome(espaco.getNome());

		historico.setSolicitacoes(solicitacoes.stream()
				.map(solicitacaoMapper::toDTO)
				.collect(Collectors.toList()));

		return ResponseEntity.ok(historico);
	}

	@PostMapping
	public ResponseEntity<?> criar(@RequestBody SolicitacaoDTO dto) {
		try {
			Solicitacao entidade = solicitacaoMapper.toEntity(dto);
			Solicitacao salva = solicitacaoService.salvar(entidade);
			return ResponseEntity.ok(solicitacaoMapper.toDTO(salva));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/pendentes")
	public ResponseEntity<List<SolicitacaoDTO>> listarPendentes() {
		List<Solicitacao> pendentes = solicitacaoService.buscarPorStatus("PENDENTE");
		List<SolicitacaoDTO> pendentesDTO = pendentes.stream()
				.map(solicitacaoMapper::toDTO)
				.toList();

		return ResponseEntity.ok(pendentesDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SolicitacaoDTO> buscarPorId(@PathVariable Long id) {
		return solicitacaoService.buscarPorID(id)
				.map(solicitacaoMapper::toDTO)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<SolicitacaoDTO>> buscarPorUsuario(@PathVariable Long usuarioId) {
		List<Solicitacao> solicitacoes = solicitacaoService.buscarPorUsuarioId(usuarioId);
		List<SolicitacaoDTO> dtos = solicitacoes.stream()
				.map(solicitacaoMapper::toDTO)
				.toList();

		return ResponseEntity.ok(dtos);
	}


	@PutMapping("/{id}")
	public ResponseEntity<SolicitacaoDTO> atualizar(@PathVariable Long id, @RequestBody SolicitacaoDTO dto) {
		dto.setId(id);
		return ResponseEntity.ok(
				solicitacaoMapper.toDTO(
						solicitacaoService.salvar(
								solicitacaoMapper.toEntity(dto)
						)
				)
		);
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<SolicitacaoDTO> atualizarStatus(
			@PathVariable Long id,
			@RequestBody AtualizarStatusDTO dto) {

		return ResponseEntity.of(
				solicitacaoService.buscarPorID(id)
						.map(solicitacao -> {
							solicitacao.setStatus(dto.getStatus());
							return solicitacaoMapper.toDTO(solicitacaoService.salvar(solicitacao));
						})
		);
	}

	@GetMapping("/pendentes/count")
	public Long contarPendentes() {
		return solicitacaoService.contarPendentes();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		solicitacaoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}