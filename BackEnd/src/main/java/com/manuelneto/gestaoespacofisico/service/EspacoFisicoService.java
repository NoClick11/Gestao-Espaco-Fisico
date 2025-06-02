package com.manuelneto.gestaoespacofisico.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuelneto.gestaoespacofisico.entity.EspacoFisico;
import com.manuelneto.gestaoespacofisico.repository.EspacoFisicoRepository;

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

    public EspacoFisico buscarPorNome(String nome) {
        return espacoRepository.findByNome(nome);
    }
    
    public void deletar(Long id) {
        espacoRepository.deleteById(id);
    }
}
