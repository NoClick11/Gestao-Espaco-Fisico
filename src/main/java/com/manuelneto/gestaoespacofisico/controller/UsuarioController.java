package com.manuelneto.gestaoespacofisico.controller;

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

import com.manuelneto.gestaoespacofisico.entity.Usuario;
import com.manuelneto.gestaoespacofisico.service.UsuarioService;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> listarTodos() {
		return usuarioService.listarTodos();
	}
	
	@PostMapping
	public Usuario criar(@RequestBody Usuario usuario) {
		return usuarioService.salvar(usuario);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
    	Usuario usuarioexistente = usuarioService.buscarPorId(id);
    	if (usuarioexistente != null) {
    		usuario.setId(id);
    		return ResponseEntity.ok(usuarioService.salvar(usuario));
    	}
    	return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    	usuarioService.deletar(id);
    	return ResponseEntity.noContent().build();
    }
	
}
