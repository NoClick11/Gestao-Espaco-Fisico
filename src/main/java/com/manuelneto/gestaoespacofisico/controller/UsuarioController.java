package com.manuelneto.gestaoespacofisico.controller;

import com.manuelneto.gestaoespacofisico.dto.UsuarioDTO;
import com.manuelneto.gestaoespacofisico.entity.Usuario;
import com.manuelneto.gestaoespacofisico.mapper.UsuarioMapper;
import com.manuelneto.gestaoespacofisico.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioMapper usuarioMapper;

	@GetMapping
	public List<UsuarioDTO> listarTodos() {
		return usuarioService.listarTodos()
				.stream()
				.map(usuarioMapper::toDTO)
				.collect(Collectors.toList());
	}

	@PostMapping
	public UsuarioDTO criar(@RequestBody UsuarioDTO dto) {
		Usuario usuario = usuarioMapper.toEntity(dto);
		usuario = usuarioService.salvar(usuario);
		return usuarioMapper.toDTO(usuario);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
		Usuario usuario = usuarioService.buscarPorId(id);
		return usuario != null ? ResponseEntity.ok(usuarioMapper.toDTO(usuario)) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
		Usuario existente = usuarioService.buscarPorId(id);
		if (existente != null) {
			Usuario usuario = usuarioMapper.toEntity(dto);
			usuario.setId(id);
			Usuario atualizado = usuarioService.salvar(usuario);
			return ResponseEntity.ok(usuarioMapper.toDTO(atualizado));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
