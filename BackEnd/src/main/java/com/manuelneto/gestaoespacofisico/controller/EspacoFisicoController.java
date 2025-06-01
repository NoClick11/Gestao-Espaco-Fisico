package com.manuelneto.gestaoespacofisico.controller;

import com.manuelneto.gestaoespacofisico.dto.EspacoFisicoDTO;
import com.manuelneto.gestaoespacofisico.entity.EspacoFisico;
import com.manuelneto.gestaoespacofisico.mapper.EspacoFisicoMapper;
import com.manuelneto.gestaoespacofisico.service.EspacoFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/espacos")
@CrossOrigin(origins = "*")
public class EspacoFisicoController {

	@Autowired
	private EspacoFisicoService espacoService;

	@Autowired
	private EspacoFisicoMapper espacoFisicoMapper;

	@GetMapping
	public List<EspacoFisicoDTO> listarTodos() {
		return espacoService.listarTodos()
				.stream()
				.map(espacoFisicoMapper::toDTO)
				.collect(Collectors.toList());
	}

	@PostMapping
	public EspacoFisicoDTO criar(@RequestBody EspacoFisicoDTO dto) {
		EspacoFisico espaco = espacoFisicoMapper.toEntity(dto);
		espaco = espacoService.salvar(espaco);
		return espacoFisicoMapper.toDTO(espaco);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EspacoFisicoDTO> buscarPorId(@PathVariable Long id) {
		EspacoFisico espaco = espacoService.buscarPorId(id);
		return espaco != null ? ResponseEntity.ok(espacoFisicoMapper.toDTO(espaco)) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<EspacoFisicoDTO> atualizar(@PathVariable Long id, @RequestBody EspacoFisicoDTO dto) {
		EspacoFisico existente = espacoService.buscarPorId(id);
		if (existente != null) {
			EspacoFisico espaco = espacoFisicoMapper.toEntity(dto);
			espaco.setId(id);
			EspacoFisico atualizado = espacoService.salvar(espaco);
			return ResponseEntity.ok(espacoFisicoMapper.toDTO(atualizado));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		espacoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
