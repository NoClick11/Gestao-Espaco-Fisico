package com.manuelneto.gestaoespacofisico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manuelneto.gestaoespacofisico.entity.EspacoFisico;
import com.manuelneto.gestaoespacofisico.service.EspacoFisicoService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/espacos")
@CrossOrigin(origins = "*")
public class EspacoFisicoController {
	
	@Autowired
	private EspacoFisicoService espacoService;
	
	@GetMapping
	public List<EspacoFisico> listarTodos() {
		return espacoService.listarTodos();
	}
	
	@PostMapping
	public EspacoFisico criar(@RequestBody EspacoFisico espaco) {
		return espacoService.salvar(espaco);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EspacoFisico> buscarPorId(@PathVariable Long id) {
		EspacoFisico espaco = espacoService.buscarPorId(id);
		return espaco != null ? ResponseEntity.ok(espaco) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EspacoFisico> atualizar(@PathVariable Long id, @RequestBody EspacoFisico espaco) {
		EspacoFisico espacoExistente = espacoService.buscarPorId(id);
		if (espacoExistente != null) {
			espaco.setId(id);
			return ResponseEntity.ok(espacoService.salvar(espaco));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		espacoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
