package com.manuelneto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manuelneto.entitys.EspacoFisico;

@Repository
public interface EspacoFisicoRepository extends JpaRepository<EspacoFisico, Long> {

}
