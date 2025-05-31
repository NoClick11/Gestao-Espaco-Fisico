package com.manuelneto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manuelneto.entitys.Solicitacao;
import com.manuelneto.service.SolicitacaoService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class SolicitacaoController {
	
	@Autowired
	private SolicitacaoService solicitacaoService;
	
	@GetMapping
	public List<Solicitacao> listarTodas() {
		return solicitacaoService.listarTodas();
	}
	
	@PostMapping
	public Solicitacao criar(@RequestBody Solicitacao solicitacao) {
		if (solicitacao.getStatus() == null) {
			solicitacao.setStatus("PENDENTE");
		}
		return solicitacaoService.salvar(solicitacao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Solicitacao> buscarPorId(@PathVariable Long id) {
		Solicitacao solicitacao = solicitacaoService.buscarPorID(id);
		return solicitacao != null ? ResponseEntity.ok(solicitacao) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Solicitacao> atualizar(@PathVariable Long id, @RequestBody Solicitacao solicitacao) {
		Solicitacao solicitacaoExistente = solicitacaoService.buscarPorID(id);
		if (solicitacaoExistente != null) {
			solicitacao.setId(id);
			return ResponseEntity.ok(solicitacaoService.salvar(solicitacao));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Solicitacao> atualizarStatus(@PathVariable Long id, @RequestBody String status) {
		Solicitacao solicitacao = solicitacaoService.buscarPorID(id);
		if (solicitacao != null) {
			solicitacao.setStatus(status);
			return ResponseEntity.ok(solicitacaoService.salvar(solicitacao));
		}
		return ResponseEntity.notFound().build();
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
