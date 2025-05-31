package com.manuelneto.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuelneto.repository.EspacoFisicoRepository;

@Service
public class EspacoFisicoService {
	
	@Autowired
	private EspacoFisicoRepository espacoRepository;
	
	public List<EspacoFisicoService> listarTodos() {
		return espacoRepository.findAll();
	}
	
	public EspacoFisicoService salvar(EspacoFisicoService espaco) {
		return espacoRepository.save(espaco);
	}
	
	public com.manuelneto.entitys.EspacoFisico buscarPorId(Long id) {
		return espacoRepository.findById(id).orElse(null);
	}
	
}
