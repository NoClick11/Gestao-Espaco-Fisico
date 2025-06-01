package com.manuelneto.gestaoespacofisico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manuelneto.gestaoespacofisico.entity.EspacoFisico;

@Repository
public interface EspacoFisicoRepository extends JpaRepository<EspacoFisico, Long> {
}
