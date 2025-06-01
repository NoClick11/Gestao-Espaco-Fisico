package com.manuelneto.gestaoespacofisico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuelneto.gestaoespacofisico.entity.Equipamento;
import com.manuelneto.gestaoespacofisico.repository.EquipamentoRepository;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public List<Equipamento> listarTodos() {
        return equipamentoRepository.findAll();
    }

    public Equipamento salvar(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    public Equipamento buscarPorId(Long id) {
        return equipamentoRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        equipamentoRepository.deleteById(id);
    }
}