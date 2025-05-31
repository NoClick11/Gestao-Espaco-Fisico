package com.manuelneto.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuelneto.entitys.EspacoFisico;
import com.manuelneto.repository.EspacoFisicoRepository;

@Service
public class EspacoFisicoService {
    
    @Autowired
    private EspacoFisicoRepository espacoRepository;
    
    public List<EspacoFisico> listarTodos() {
        return espacoRepository.findAll();
    }
    
    public EspacoFisico salvar(EspacoFisico espaco) {
        return espacoRepository.save(espaco);
    }
    
    public EspacoFisico buscarPorId(Long id) {
        return espacoRepository.findById(id).orElse(null);
    }
    
    public void deletar(Long id) {
        espacoRepository.deleteById(id);
    }
}
