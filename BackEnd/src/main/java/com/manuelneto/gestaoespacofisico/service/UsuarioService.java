package com.manuelneto.gestaoespacofisico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuelneto.gestaoespacofisico.entity.Usuario;
import com.manuelneto.gestaoespacofisico.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}

	public Usuario salvar(Usuario usuario) {
		Usuario existente = usuarioRepository.findByEmail(usuario.getEmail());
		if (existente != null && !existente.getId().equals(usuario.getId())) {
			throw new RuntimeException("Email já cadastrado.");
		}
		return usuarioRepository.save(usuario);
	}
	
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario buscarPorEmail(String Email) {
		return usuarioRepository.findByEmail(Email);
	}
	
	public void deletar(Long id) {
		usuarioRepository.deleteById(id);
	}
}
