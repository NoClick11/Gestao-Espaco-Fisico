package com.manuelneto.gestaoespacofisico.controller;

import com.manuelneto.gestaoespacofisico.dto.EquipamentoDTO;
import com.manuelneto.gestaoespacofisico.entity.Equipamento;
import com.manuelneto.gestaoespacofisico.mapper.EquipamentoMapper;
import com.manuelneto.gestaoespacofisico.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/equipamentos")
@CrossOrigin(origins = "*")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @Autowired
    private EquipamentoMapper equipamentoMapper;

    @GetMapping
    public List<EquipamentoDTO> listarTodos() {
        return equipamentoService.listarTodos()
                .stream()
                .map(equipamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public EquipamentoDTO criar(@RequestBody EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = equipamentoMapper.toEntity(equipamentoDTO);
        equipamento = equipamentoService.salvar(equipamento);
        return equipamentoMapper.toDTO(equipamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> buscarPorId(@PathVariable Long id) {
        Equipamento equipamento = equipamentoService.buscarPorId(id);
        return equipamento != null ? ResponseEntity.ok(equipamentoMapper.toDTO(equipamento)) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> atualizar(@PathVariable Long id, @RequestBody EquipamentoDTO equipamentoDTO) {
        Equipamento existente = equipamentoService.buscarPorId(id);
        if (existente != null) {
            Equipamento equipamento = equipamentoMapper.toEntity(equipamentoDTO);
            equipamento.setId(id);
            Equipamento atualizado = equipamentoService.salvar(equipamento);
            return ResponseEntity.ok(equipamentoMapper.toDTO(atualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        equipamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}