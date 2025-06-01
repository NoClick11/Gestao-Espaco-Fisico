package com.manuelneto.gestaoespacofisico.controller;

import com.manuelneto.gestaoespacofisico.dto.SolicitacaoDTO;
import com.manuelneto.gestaoespacofisico.mapper.SolicitacaoMapper;
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

	@GetMapping
	public List<SolicitacaoDTO> listarTodas() {
		return solicitacaoService.listarTodas()
				.stream()
				.map(solicitacaoMapper::toDTO)
				.collect(Collectors.toList());
	}

	@PostMapping
	public SolicitacaoDTO criar(@RequestBody SolicitacaoDTO dto) {
		return solicitacaoMapper.toDTO(
				solicitacaoService.salvar(
						solicitacaoMapper.toEntity(dto)
				)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SolicitacaoDTO> buscarPorId(@PathVariable Long id) {
		return solicitacaoService.buscarPorID(id)
				.map(solicitacaoMapper::toDTO)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
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
	public ResponseEntity<SolicitacaoDTO> atualizarStatus(@PathVariable Long id, @RequestBody String status) {
		return ResponseEntity.of(
				solicitacaoService.buscarPorID(id)
						.map(solicitacao -> {
							solicitacao.setStatus(status);
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