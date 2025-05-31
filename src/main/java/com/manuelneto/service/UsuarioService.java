package com.manuelneto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuelneto.entitys.Usuario;
import com.manuelneto.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}
	
	public Usuario salvar(Usuario usuario) {
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
