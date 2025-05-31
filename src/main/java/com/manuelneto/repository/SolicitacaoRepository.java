package com.manuelneto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manuelneto.entitys.Solicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{
	List<Solicitacao> findByStatus(String status);
	
	@Query("SELECT COUNT(s) From Solicitacao s WHERE s.status = 'PENDENTE'")
	Long CountPendentes();

}
